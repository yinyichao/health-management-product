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
import lombok.NonNull;

@Data
@NoArgsConstructor
@ApiModel("问卷收集表;实体类")
@SuppressWarnings("serial")
@TableName("tb_questionnaire_item")
@ExcelIgnoreUnannotated
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
    private Integer id;
//问卷主表ID
     @ApiModelProperty(value = "问卷主表ID")
    private Integer questionnaireId;
//推送人
    @ApiModelProperty(value = "推送人")
    @ExcelProperty(value = "发卷人")
    private String pushName;
    //填写人
    @ApiModelProperty(value = "填写人")
    @ExcelProperty(value = "填写人")
    private String name;
    @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")
    @ExcelProperty(value = "风险类型")
    private String label;
    @ApiModelProperty(value = "风险说明")
    @ExcelProperty(value = "风险说明")
    private String labelContent;
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "采集时间")
    private Date createdTime;
    @ApiModelProperty(value = "状态（作废、有效）")
    @ExcelProperty(value = "状态")
    private String state = "有效";
//填写人电话
     @ApiModelProperty(value = "填写人电话")
    private String telephone;

//内容
     @ApiModelProperty(value = "内容")
    private String content;
//状态（作废、有效）

//创建人
     @ApiModelProperty(value = "创建人")
     @NonNull
    private String createdUser;
//创建时间

//更新人
     @ApiModelProperty(value = "更新人")
    private String updatedUser;
//更新时间
     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
//规则类型（高风险、中风险、低风险）

//风险说明

//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")
    private Integer del;

}

