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
@ApiModel("面见管理表;实体类")
@SuppressWarnings("serial")
@TableName("tb_view")
/**
 * 面见管理表;(TbView)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
public class TbView extends Model<TbView> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.INPUT)
    @ExcelProperty(value = "id")
    private Integer id;
//被拜访者
     @ApiModelProperty(value = "被拜访者")

    @ExcelProperty(value = "visitedPerson")
    private String visitedPerson;
//拜访内容
     @ApiModelProperty(value = "拜访内容")

    @ExcelProperty(value = "content")
    private String content;
//备注
     @ApiModelProperty(value = "备注")

    @ExcelProperty(value = "remark")
    private String remark;
//拜访者
     @ApiModelProperty(value = "拜访者")

    @ExcelProperty(value = "visitor")
    private String visitor;
//拜访地点
     @ApiModelProperty(value = "拜访地点")

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
    //风险说明
    @ApiModelProperty(value = "风险说明")

    @ExcelProperty(value = "labelContent")
    private String labelContent;
    //状态（删除）
    @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;
    //状态（作废、有效）
    @ApiModelProperty(value = "状态（作废、有效）")

    @ExcelProperty(value = "state")
    private String state = "有效";
}

