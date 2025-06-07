package com.yins.health.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("部门表实体类")
@SuppressWarnings("serial")
@TableName("tb_dept")
/**
 * 部门表(TbDept)表实体类
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
public class TbDept extends Model<TbDept> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.INPUT)
    @ExcelProperty(value = "id")
    private Integer id;
//部门名称
     @ApiModelProperty(value = "部门名称")

    @ExcelProperty(value = "name")
    private String name;
//父节点
     @ApiModelProperty(value = "父节点")

    @ExcelProperty(value = "parentid")
    private Integer parentid;
//排序
     @ApiModelProperty(value = "排序")
     @TableField("`order`")
    @ExcelProperty(value = "order")
    private String order;
//部门负责人用户表ID
/*     @ApiModelProperty(value = "部门负责人用户表ID")

    @ExcelProperty(value = "departmentLeader")
    private String departmentLeader;*/

}

