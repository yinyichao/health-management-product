package com.yins.health.conf;

/**
 *
 * @Description
 * @Author Darius
 * @Version 1.0
 **/
public class AccountConfig {

    /**
     * 账号密码加密的盐
     */
    public static final String ACCOUNT_SALT = "ddd.net168";

    /**
     * 默认存储空间大小  1000MB
     */
    public static final Long DEFAULT_STORAGE_SIZE = 1024 * 1024  * 1000L;

    /**
     * 根文件夹名称
     */
    public static final String ROOT_FOLDER_NAME = "全部文件夹";

    /**
     * 根文件夹的父ID
     */
    public static final Long ROOT_PARENT_ID = 0L;

    /**
     * 网盘前端地址
     */
    public static final String PAN_FRONT_DOMAIN_SHARE_API = "127.0.0.1:9999/share/";

}