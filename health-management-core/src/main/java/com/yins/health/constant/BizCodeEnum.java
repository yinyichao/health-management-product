package com.yins.health.constant;

import lombok.Getter;

public enum BizCodeEnum {
    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001, "账号已经存在"),
    ACCOUNT_UNREGISTER(250002, "账号不存在"),
    ACCOUNT_PWD_ERROR(250003, "账号或者密码错误"),
    ACCOUNT_UNLOGIN(250004, "账号未登录"),


    UPLOAD_FAILURE(220400, "上传失败"),
    RETURN_FAILURE(220444, "返回值错误"),



    /**
     * 文件操作相关
     */
    FILE_NOT_EXISTS(220404, "文件不存在"),
    FILE_RENAME_REPEAT(220405, "文件名重复"),
    FILE_DEL_BATCH_ILLEGAL(220406, "文件删除参数错误"),
    FILE_RECYCLE_ILLEGAL(280406,"回收站操作参数错误"),
    FILE_TYPE_ERROR(220407, "文件类型错误"),
    FILE_CHUNK_TASK_NOT_EXISTS(230408, "分片任务不存在"),
    FILE_CHUNK_NOT_ENOUGH(230409, "分片数量不匹配，合并不够"),
    FILE_STORAGE_NOT_ENOUGH(240403, "存储空间不足"),
    FILE_TARGET_PARENT_ILLEGAL(250403, "目标父级目录不合法"),
    SHARE_CANCEL_ILLEGAL(260403, "取消分享失败,参数不合法"),
    SHARE_CODE_ILLEGAL(260404, "分享码不合法"),
    SHARE_NOT_EXIST(260405, "分享不存在"),
    SHARE_CANCEL(260406, "分享已取消"),
    SHARE_EXPIRED(260407, "分享已过期"),
    SHARE_FILE_ILLEGAL(260408, "分享的文件不合规"),

    FILE_MOVE_ERROR(270101, "文件移动部分失败"),
    FILE_BATCH_UPDATE_ERROR(270101, "文件批量操作错误"),


    /**
     * 文件操作相关
     */
    AI_APP_NOT_EXISTS(280404, "AI应用不存在"),
    AI_APP_EXISTS(280404, "AI应用已存在"),
    AI_APP_DEL_ERROR(280405, "AI应用删除失败"),
    AI_APP_UPDATE_ERROR(280406, "AI应用更新失败"),
    AI_APP_CREATE_ERROR(280407, "AI应用创建失败"),
    DUPLICATE_CREATE_ERROR(280408, "待办已添加");
    @Getter
    private String message;

    @Getter
    private int code;

    private BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}