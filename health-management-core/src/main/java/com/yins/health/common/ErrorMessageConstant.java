package com.yins.health.common;

public final class ErrorMessageConstant {


    private ErrorMessageConstant() {
    }

    public static int SUCCESS = 200;//成功
    public static int ERROR_SYSTEMERROR = 500;//系统异常
    public static int ERROR_BUSINESS = 501;//系统业务异常
    public static int ERROR_LOGIN_OUT_TIME = 1000;//拿不到用户信息报错
    public static int ERROR_PARAMETER = 1001;//请求参数错误
    public static int ERROR_UNSUPPORT_PROTOCOL = 1002;//协议版本错误
    public static int ERROR_SESSION = 1003;//用户身份认证失败
    public static int ERROR_REGISTER = 1004;//注册失败
    public static int ERROR_SMSCODE = 1005;//错误验证码
    public static int ERROR_USER_NOT_EXIST = 1006;//用户不存在
    public static int ERROR_PASSWORD = 1007;//密码错误
    public static int ERROR_USER_EXIST = 1008;//用户已存在
    public static int ERROR_AUTHORITY_EXCEED = 1009;//用户身份认证失败

    public static String MSG_SUCCESS = "操作成功";
    public static String MSG_ERROR_SYSTEMERROR = "系统异常";
    public static String MSG_ERROR_PARAMETER = "请求参数错误";
    public static String MSG_ERROR_UNSUPPORT_PROTOCOL = "协议版本错误";
    public static String MSG_ERROR_SESSION = "用户身份认证失败";
    public static String MSG_ERROR_REGISTER = "注册失败";
    public static String MSG_ERROR_SMSCODE = "验证码错误";
    public static String MSG_ERROR_USER_NOT_EXIST = "用户不存在";
    public static String MSG_ERROR_PASSWORD = "密码错误";
    public static String MSG_USER_EXIST = "用户已存在";
    public static String MSG_AUTHORITY_EXCEED = "用户身份认证失败，没有权限";

}