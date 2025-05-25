package com.yins.health.common;

import lombok.Data;

/**
 * 业务异常
 *
 * @author wvp
 */
@Data
public final class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public static final Integer DEFULT_ERROR_CODE = 501; //默认501前端提示
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示（前端message）
     */
    private String message;

    /**
     * 错误明细，后台调试的提示（后端message）
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {}

    public ServiceException(String message, Integer code, String detailMessage) {
        this.message = message;
        this.code = code;
        this.detailMessage = detailMessage;
    }

    public ServiceException(String message) {
        this(message, DEFULT_ERROR_CODE, "");
    }

    public ServiceException(String message, Integer code) {
        this(message, code, "");
    }

    /**
     * 前后端都用Exception的message
     * @param detailE
     */
    public ServiceException(Exception detailE) {
        this(detailE.getMessage(), DEFULT_ERROR_CODE, detailE.getMessage());
    }

    /**
     * 前端自定义massage，后端用Exception的massage
     * @param message
     * @param detailE
     */
    public ServiceException(String message, Exception detailE) {
        this(message, DEFULT_ERROR_CODE, detailE.getMessage());
    }

    /**
     * 前端自定义massage，后端用Exception的massage
     * @param message
     * @param code
     * @param detailE
     */
    public ServiceException(String message, Integer code, Exception detailE) {
        this(message, code, detailE.getMessage());
    }





}