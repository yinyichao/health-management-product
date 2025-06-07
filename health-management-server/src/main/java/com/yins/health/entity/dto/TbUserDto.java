package com.yins.health.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("用户表;实体类")

public class TbUserDto {

    private String id;

    //用户名
    @ApiModelProperty(value = "用户名")
    @ExcelProperty(value = "username")
    private String username;


    @ApiModelProperty(value = "0、未选中，1、选中")
    private Integer isCheck;
}

