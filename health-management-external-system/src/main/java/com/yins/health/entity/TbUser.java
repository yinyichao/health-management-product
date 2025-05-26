package com.yins.health.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@NoArgsConstructor
@ApiModel("用户表;实体类")
@SuppressWarnings("serial")
@TableName("tb_user")
/**
 * 用户表;(TbUser)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
public class TbUser extends Model<TbUser> {
    @Schema(description = "ID")
    @TableId(type = IdType.INPUT)
    private Integer id;
    //创建人
     @ApiModelProperty(value = "创建人")

    @ExcelProperty(value = "createdUser")
    private String createdUser;
//创建时间
     @ApiModelProperty(value = "创建时间")
     @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "createdTime")
    private Date createdTime;
//更新人
     @ApiModelProperty(value = "更新人")

    @ExcelProperty(value = "updatedUser")
    private String updatedUser;
//更新时间
     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "updatedTime")
    private Date updatedTime;
//用户名
     @ApiModelProperty(value = "用户名")

    @ExcelProperty(value = "username")
    private String username;
//密码
     @ApiModelProperty(value = "密码")

    @ExcelProperty(value = "password")
    private String password;
    //手机号
    @ApiModelProperty(value = "手机号")

    @ExcelProperty(value = "telephone")
    private String telephone;
}

