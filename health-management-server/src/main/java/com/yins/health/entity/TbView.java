package com.yins.health.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
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
@ExcelIgnoreUnannotated
/**
 * 面见管理表;(TbView)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
public class TbView extends Model<TbView> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    private Integer id;

    @ExcelProperty(value = "拜访者")
    private String username;

    @ApiModelProperty(value = "被拜访者")
    @ExcelProperty(value = "被拜访者")
    private String visitedPerson;

    @ApiModelProperty(value = "陪访者")
    @ExcelProperty(value = "陪访者")
    private String visitor;

    @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")
    @ExcelProperty(value = "风险类型")
    private String label;

    @ApiModelProperty(value = "风险说明")
    @ExcelProperty(value = "风险说明")
    private String labelContent;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "提交时间")
    private Date createdTime;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态（作废、有效）")
    @ExcelProperty(value = "状态")
    private String state = "有效";

    @ApiModelProperty(value = "拜访内容")
    private String content;

    @ApiModelProperty(value = "拜访地点")
    private String location;

    @ApiModelProperty(value = "图片1")
    private String pic1;

    @ApiModelProperty(value = "图片2")
    private String pic2;

     @ApiModelProperty(value = "图片3")
    private String pic3;


     @ApiModelProperty(value = "更新人")
    private String updatedUser;

     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty(value = "删除字段（0、未删，1、删除）")
    private Integer del;
    //状态（作废、有效）
    @ApiModelProperty(value = "创建人")
    private String createdUser;
}

