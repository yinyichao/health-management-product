package com.yins.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user_online_log")
@Schema(description = "用户在线时长记录表")
public class UserOnlineLog implements Serializable {

    @TableId(type = IdType.AUTO)
    @Schema(description = "主键ID")
    private Integer id;

    @Schema(description = "账号ID")
    private String accountId;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "退出时间")
    private LocalDateTime logoutTime;

    @Schema(description = "在线时长（秒）")
    private Long onlineSecs;

    @Schema(description = "登录IP地址")
    private String ipAddress;

    @Schema(description = "用户浏览器 UA（User-Agent）")
    private String userAgent;

    @Schema(description = "租户ID")
    private Long tenantId;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;
}
