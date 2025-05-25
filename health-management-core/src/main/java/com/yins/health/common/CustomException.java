package com.yins.health.common;

/**
 * @ClassName：CustomException
 * @Description：自定义Service异常类
 * @Author：apple
 * @Date：2020/7/29 9:07
 * @Versiion：1.0
 */
public class CustomException extends RuntimeException{
    private Integer code;
    private String msg;

    public CustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
