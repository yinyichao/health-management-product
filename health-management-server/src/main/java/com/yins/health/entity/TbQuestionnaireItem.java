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
@ApiModel("问卷收集表;实体类")
@SuppressWarnings("serial")
@TableName("tb_questionnaire_item")
/**
 * 问卷收集表;(TbQuestionnaireItem)表实体类
 *
 * @author yinyichao
 * @since 2025-05-28 14:06:23
 */
public class TbQuestionnaireItem extends Model<TbQuestionnaireItem> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//问卷主表ID
     @ApiModelProperty(value = "问卷主表ID")

    @ExcelProperty(value = "questionnaireId")
    private Integer questionnaireId;
//推送人
     @ApiModelProperty(value = "推送人")

    @ExcelProperty(value = "pushName")
    private String pushName;
//填写人电话
     @ApiModelProperty(value = "填写人电话")

    @ExcelProperty(value = "telephone")
    private String telephone;
//填写人
     @ApiModelProperty(value = "填写人")

    @ExcelProperty(value = "name")
    private String name;
//内容
     @ApiModelProperty(value = "内容")

    @ExcelProperty(value = "content")
    private String content;
//状态（作废、有效）
     @ApiModelProperty(value = "状态（作废、有效）")

    @ExcelProperty(value = "state")
    private String state = "有效";
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
//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;

}

