package com.bonc.businesscircle.response;


/**
 * 响应码
 *
 * @author JokerJodas
 * @date 2018.6.11
 */
public interface IResponseEnum {
    /**
     * 返回码
     *
     * @return
     */
    int code();

    /**
     * 返回文本信息
     *
     * @return
     */
    String msg();
}
