package com.yins.health.base.mvc;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author wvp
 */
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认成功code
     */
    public static final Integer SUCCESS = 200;

    /**
     * 默认失败code：500前端不提示，501前端提示
     */
    public static final Integer FAIL = 501;

    private Integer code;

    private String msg;

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> fail(Integer code, String msg, T data) {
        return restResult(data, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
