package com.yins.health.common;


/**
 * @author PoPo
 */

public enum ReturnCode {
    SUCCESS(200, "SUCCESS"),
    FAIL(500, "非提示错误"),
    BUSIBESS_ERROR(501, "通用业务错误"),
    LOGIN_INFO_ERROR(-1, "登录信息错误，重新登录");


    private Integer code;
    private String text;


    ReturnCode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }


}
