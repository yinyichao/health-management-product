package com.yins.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@NoArgsConstructor
@ApiModel("用户-部门关联表实体类")
@SuppressWarnings("serial")
@TableName("tb_user_dept")
/**
 * 用户-部门关联表(TbUserDept)表实体类
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
public class TbUserDept extends Model<TbUserDept> {

    @ExcelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;
//用户表ID
     @ApiModelProperty(value = "用户表ID")

    @ExcelProperty(value = "userId")
    private String userId;
//部门表ID
     @ApiModelProperty(value = "部门表ID")

    @ExcelProperty(value = "deptId")
    private Integer deptId;

}

