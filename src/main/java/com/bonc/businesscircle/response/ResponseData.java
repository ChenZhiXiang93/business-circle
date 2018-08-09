package com.bonc.businesscircle.response;

import com.bonc.businesscircle.model.PageData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JokerJodas
 * @date 2018.6.11
 */
public class ResponseData {
    /**
     * 仅反馈成功，无详细数据，请用这个
     */
    public static final ResponseData SUCCESS = new ResponseData(CommResponseEnum.SUCCESS);

    /**
     * 未知错误，无详细数据，请用这个
     */
    public static final ResponseData FAILURE = new ResponseData(CommResponseEnum.FAILURE);

    private int code;
    private String msg;
    private Object data;

    /**
     * 异常码反馈
     *
     * @param responseEnum
     */
    public ResponseData(IResponseEnum responseEnum) {
        this.code = responseEnum.code();
        this.msg = responseEnum.msg();
    }

    /**
     * 异常时还需要数据的情况
     *
     * @param responseEnum
     * @param data
     */
    public ResponseData(IResponseEnum responseEnum, Object data) {
        this.code = responseEnum.code();
        this.msg = responseEnum.msg();
        this.data = data;
    }

    /**
     * 成功消息
     *
     * @param map
     */
    public static String turnResponse(Object map) throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        if (map instanceof PageData) {
            resMap.put("code", "200");
            resMap.put("msg", "操作成功");
            resMap.put("totalCount", ((PageData) map).getTotalCount());
            resMap.put("data", ((PageData) map).getRecordList());
        } else {
            resMap.put("code", "200");
            resMap.put("msg", "操作成功");
            resMap.put("data", map);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return  mapper.writeValueAsString(resMap);
    }

    /**
     * 成功消息
     *
     * @param
     */
    public static String OKResponse() throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", "200");
        resMap.put("msg", "操作成功");
        return new ObjectMapper().writeValueAsString(resMap);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


