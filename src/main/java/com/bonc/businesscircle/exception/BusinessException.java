package com.bonc.businesscircle.exception;


import com.bonc.businesscircle.response.IResponseEnum;

/**
 * 有业务异常就抛这个
 * 非业务异常、不可控的异常不处理或直接抛出，交给GlobalExceptionHandler处理。
 *
 * @author wangxiaoyun
 * @date 2018/6/11
 */
public class BusinessException extends RuntimeException {
    /**
     * 业务异常码
     */
    private IResponseEnum responseEnum;

    private Object data;

    public BusinessException(IResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public BusinessException(IResponseEnum responseEnum, Object data) {
        this(responseEnum);
        this.data = data;
    }

    public BusinessException(IResponseEnum responseEnum, Throwable cause) {
        super(cause);
        this.responseEnum = responseEnum;
    }

    public BusinessException(IResponseEnum responseEnum, Object data, Throwable cause) {
        this(responseEnum, cause);
        this.data = data;
    }

    /**
     * 业务异常类，没有则表示未知异常咯
     *
     * @return
     */
    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String getMessage() {
        return responseEnum == null ? null : responseEnum.msg();
    }
}
