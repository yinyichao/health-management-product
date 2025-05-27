package com.yins.health.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Darius
 * @since 2024-12-24
 */
@Getter
@Setter
@Schema(name = "AccountDO", description = "用户信息")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {

    @Schema(description = "ID")
    private Integer id;
    //创建人
    @ApiModelProperty(value = "创建人")
    private String createdUser;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    //更新人
    @ApiModelProperty(value = "更新人")
    private String updatedUser;
    //更新时间
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    //用户名
    @ApiModelProperty(value = "用户名")
    private String username;
    //密码
    @ApiModelProperty(value = "密码")
    private String password;
    //手机号
    @ApiModelProperty(value = "手机号")
    private String telephone;

}
