package com.yins.health.constant;


/**
 * @author yehun
 */

public enum ReturnCode {
    /**/
    SUCCESS(200, "SUCCESS"),
    CRON_EXPRESSION_ERROR(311, "cron表达式不正确"),
    SCHEDULER_EXPRESSION_ERROR(312, "调度异常"),
    SCHEDULER_LIMIT_ERROR(313, "请求次数过多"),
    PARAMETER(400, "PARAMETER INVALID"),
    TOKEN_INVALID(401, "登录状态已失效，请重新登录"),
    CODE_INVALID(402, "验证码不正确"),
    PERMISSION(403, "PERMISSION DENIED"),
    NOT_FOUND(404, "NOT_FOUND"),
    /*校验公共错误*/
    PARAMS_CHECK_ERROR(405, "参数校验公共错误"),
    MYSQL_ERROR(406, "数据库执行出错"),
    NEBULAGRAPH_ERROR(407, "图数据库执行出错"),
    INSERT_SOME_FAIL(408, "部分数据新增失败"),
    UPDATE_SOME_FAIL(409, "部分数据修改失败"),
    DATA_EXIST(410, "数据已存在"),
    MYSQL_ERROR_CONNECT(411, "数据库连接出错"),
    OTHER_ERROR(444, "其他错误"),
    REPEAD_ADD(445, "添加了重复数据"),
    ADD_ERROR(446, "新增失败"),
    EDIT_ERROR(447, "修改失败"),
    DEL_ERROR(448, "删除失败"),
    FAIL(500, "FAIL"),
    USER_INFO_ERROR(501, "用户信息错误"),
    DEVICE_INFO_ERROR(502, "设备信息错误"),
    /*注册特殊错误*/
    EMAIL_PARAMETER(10000, "参数不能为空"),
    EMAIL_PARAMETER_ID(10006, "id不能为空"),
    /*注册用户错误*/
    USER_PARAMETER(10010, "参数不能为空"),
    /*注册及登录错误*/
    USER_EXIST(10030, "登录失败"),
    USER_PARAM_ACCOUNT(10032, "用户账号或密码不能为空"),
    USER_PARAM_PASSWORD(10033, "用户账号或密码不能为空"),
    /*编辑错误*/
    BLACK_LIST_SAVE_FAIL(10044, "项目信息保存失败"),
    BLACK_LIST_UPDATE_FAIL(10045, "项目信息更新失败"),
    BLACK_LIST_NOT_EXIST(10046, "项目信息不存在"),
    BLACK_LIST_DELETE_FAIL(10047, "项目信息删除失败"),
    DUPLICATE_PROJECT_NAME(10048, "项目名称已存在"),
    DUPLICATE_PROJECT_CODE(10049, "国家编码已存在"),

    /*导入校验*/
    IMPORT_VERIFY(10080, "当前表格与模板不一致，请使用模板表格操作"),
    IMPORT_VERIFY_TELEPHONE(10081, "请输入正确的手机号"),
    DATA_FORMATTING_ERROR(10082, "数据格式错误"),
    IMPORT_OVERSIZE(10083, "文件过大"),
    Abnormal_STATUS(10090,"报名状态不符合要求,无法编辑")
    ;
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
