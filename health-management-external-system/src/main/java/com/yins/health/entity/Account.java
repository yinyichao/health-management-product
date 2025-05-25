package com.yins.health.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yins.health.conf.AccountConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author my project - Darius,
 * @since 2025-01-18
 */
@Getter
@Setter
@TableName("account")
@Schema(name = "Account", description = "用户信息表")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;

    @Schema(description = "用户角色 1Admin 2Whitelist 3Stranger")
    @TableField("role")
    private String role;

    @Schema(description = "租户id 0其它 1深能")
    @TableField("tenant_id")
    private String tenantId;

    @Schema(description = "逻辑删除（1删除 0未删除）")
    @TableField("del")
    @TableLogic
    private Boolean del;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;

    @JsonIgnore
    @JSONField(serialize = false)
    public String getEncryptPwd(){
        return DigestUtils.md5DigestAsHex((AccountConfig.ACCOUNT_SALT + this.getPassword()).getBytes());
    }

}
