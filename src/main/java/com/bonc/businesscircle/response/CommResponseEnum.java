package com.bonc.businesscircle.response;

/**
 *
 * @author JokerJodas
 * @date 2018.6.11
 *
 *  */
public enum CommResponseEnum implements IResponseEnum {
    FAILURE(-1, "系统错误"),

    //用户类
    USER1(1001,"验证码或账号密码错误"),
    USER2(1002,"用户列表查询失败"),
    USER3(1003,"用户没有对应权限"),
    //任务类
    TASK1(2001,"任务查询失败"),
    TASK2(2002,"匹配结果返回查询任务失败"),
    TASK3(2003,"任务状态不是待审核"),
    TASK4(2004,"调用MPPAPI失败"),
    //合同类
    CONTRACT1(3001,"合同状态不是待审核"),
    CONTRACT2(3002,"合同状态不是待审核不能删除"),
    //系统类型
    APIERROR1(4001,"缺少文件参数"),
    APIERROR2(4002,"解密失败"),
    APIERROR3(4003,"文件类型错误"),
    APIERROR4(4004,"文件下载失败"),

    FAILURE1(0,"查无数据"),
    FAILURE2(1,"数据异常"),
    SUCCESS(200, "操作成功");

    private int respcode;
    private String respmsg;

    CommResponseEnum(int respcode, String respmsg) {
        this.respcode = respcode;
        this.respmsg = respmsg;
    }

    @Override
    public int code() {
        return this.respcode;
    }

    @Override
    public String msg() {
        return this.respmsg;
    }
}
