package com.yins.health.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yins.health.entity.TbDept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("用户表;实体类")

public class TbUserListDto {

    private String id;

    //用户名
    @ApiModelProperty(value = "用户名")
    @ExcelProperty(value = "username")
    private String username;


    private List<TbDept> deptList;
}

