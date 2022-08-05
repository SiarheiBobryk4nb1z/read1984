package com.jaemon.dingtalk.entity.enums;

/**
 * 异常枚举
 *
 * @author Jaemon@answer_ljm@163.com
 * @version 1.0
 */
public enum ExceptionEnum implements Pairs<Integer, String> {
    SEND_MSG(1000, "发送消息异常"),
    MSG_TYPE_CHECK(2000, "消息类型异常"),
    ASYNC_CALL(3000, "异步调用异常"),
    /**
     * dingTalkManagerBuilder 配置异常
     */
    CONFIG_ERROR(4000, "配置异常"),
    /**
     *
     */
    PROPERTIES_ERROR(5000, "配置文件异常"),

    UNKNOWN(9999, "未知异常")
    ;


    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String desc() {
        return message;
    }
}