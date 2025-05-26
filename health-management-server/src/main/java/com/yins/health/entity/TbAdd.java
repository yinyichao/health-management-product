package com.yins.health.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@NoArgsConstructor
@ApiModel("增员管理表;实体类")
@SuppressWarnings("serial")
@TableName("tb_add")
/**
 * 增员管理表;(TbAdd)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public class TbAdd extends Model<TbAdd> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.INPUT)
    @ExcelProperty(value = "id")
    private Integer id;
//应聘者
     @ApiModelProperty(value = "应聘者")

    @ExcelProperty(value = "name")
    private String name;
//电话
     @ApiModelProperty(value = "电话")

    @ExcelProperty(value = "telephone")
    private String telephone;
//备注
     @ApiModelProperty(value = "备注")

    @ExcelProperty(value = "remark")
    private String remark;
//面试地点
     @ApiModelProperty(value = "面试地点")

    @ExcelProperty(value = "location")
    private String location;
//图片1
     @ApiModelProperty(value = "图片1")

    @ExcelProperty(value = "pic1")
    private String pic1;
//图片2
     @ApiModelProperty(value = "图片2")

    @ExcelProperty(value = "pic2")
    private String pic2;
//图片3
     @ApiModelProperty(value = "图片3")

    @ExcelProperty(value = "pic3")
    private String pic3;
//应聘登记表
     @ApiModelProperty(value = "应聘登记表")

    @ExcelProperty(value = "registration")
    private String registration;
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
//规则类型（高风险、中风险、低风险）
     @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")

    @ExcelProperty(value = "label")
    private String label;
//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;


    //状态（作废、有效）
    @ApiModelProperty(value = "状态（作废、有效）")

    @ExcelProperty(value = "state")
    private String state = "有效";

    //风险说明
    @ApiModelProperty(value = "风险说明")

    @ExcelProperty(value = "labelContent")
    private String labelContent;
    //面试者
    @ApiModelProperty(value = "面试者")
    private String username;
}

