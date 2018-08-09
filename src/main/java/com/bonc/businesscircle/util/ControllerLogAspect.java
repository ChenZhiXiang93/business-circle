package com.bonc.businesscircle.util;

import com.bonc.businesscircle.exception.BusinessException;
import com.bonc.businesscircle.response.IResponseEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 所有controller的日志记录切面
 *
 */
@Component
@Aspect
public class ControllerLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    /**
     * 记录日志到log文件，不打印堆栈；
     * 异常堆栈全部放在GlobalExceptionHandler中打印，防止过多的重复堆栈信息
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "execution( * com..*.controller..*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        StringBuilder log = null;
        if (logger.isInfoEnabled()) {
            log = this.createLog(pjp);
        }

        Object obj;
        try {
            obj = pjp.proceed();
        } catch (BusinessException e) {
            if (log == null) {
                log = createLog(pjp);
            }
            log.append(" Failed for ");
            IResponseEnum responseEnum = e.getResponseEnum();
            if (responseEnum == null) {
                log.append(" Failed for unknown reason(this should not happen).");
            } else {
                log.append("reason: ").append(responseEnum.code()).append("-").append(responseEnum.msg());
            }
            logger.error(log.toString());

            // 抛出异常让GlobalExceptionHandler转换为ResponseData
            throw e;
        } catch (Throwable t) {
            if (log == null) {
                log = createLog(pjp);
            }
            log.append(" Failed for reason:").append(t.getMessage());
            logger.error(log.toString());

            // 抛出异常让GlobalExceptionHandler转换为ResponseData
            throw t;
        }

        if (log != null) {
            String resStr;
            if (obj == null) {
                resStr = null;
            } else {
                try {
                    resStr = new ObjectMapper().writeValueAsString(obj);
                } catch (Exception e) {
                    resStr = obj.toString();
                }
            }
            log.append(" Return value = ").append(resStr);
            logger.info(log.toString());
        }

        return obj;
    }


    /**
     * 创建日志
     *
     * @param pjp
     * @return
     */
    private StringBuilder createLog(ProceedingJoinPoint pjp) {
        StringBuilder log = new StringBuilder("invoke [");
        String targetName = pjp.getTarget().getClass().getName();
        log.append(targetName).append(".");
        String methodName = pjp.getSignature().getName();
        log.append(methodName).append("], parameter = ");
        log.append(Arrays.toString(pjp.getArgs())).append(". ");
        return log;
    }

}
