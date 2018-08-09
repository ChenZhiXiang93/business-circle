package com.bonc.businesscircle.exception;


import com.bonc.businesscircle.response.IResponseEnum;
import com.bonc.businesscircle.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 过滤所有异常，防止泄漏系统和接口细节
 *
 * @author wangxiaoyun
 * @date 2018/6/11
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可控的异常，对调用方屏蔽错误细节
     *
     * @param t
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResponseData defaultHandler(Throwable t) throws Exception {
        logger.error(t.getMessage(), t);
        return ResponseData.FAILURE;
    }

    /**
     * 处理参数校验异常
     *
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseData bindingResultHandler(BindException be, BindingResult bindingResult) throws Exception {
        throw be;
    }

    /**
     * 封装业务异常为ResponseData
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseData businessExceptionHandler(BusinessException e) throws Exception {
        IResponseEnum responseEnum = e.getResponseEnum();
        // 如果没有异常码，则按照不可控异常处理
        if (responseEnum == null) {
            return defaultHandler(e);
        }

        // 自带堆栈，则打印
        if (e.getCause() != null) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseData(responseEnum, e.getData());
    }
}
