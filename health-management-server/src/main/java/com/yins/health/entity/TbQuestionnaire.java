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
@ApiModel("采集问卷管理表;实体类")
@SuppressWarnings("serial")
@TableName("tb_questionnaire")
/**
 * 采集问卷管理表;(TbQuestionnaire)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public class TbQuestionnaire extends Model<TbQuestionnaire> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//编号
     @ApiModelProperty(value = "编号")

    @ExcelProperty(value = "ref")
    private String ref;
//名称
     @ApiModelProperty(value = "名称")

    @ExcelProperty(value = "name")
    private String name;
//说明
     @ApiModelProperty(value = "说明")

    @ExcelProperty(value = "description")
    private String description;
//内容
     @ApiModelProperty(value = "内容")

    @ExcelProperty(value = "content")
    private String content;
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
//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;
    //状态（草稿、发布）
     @ApiModelProperty(value = "状态（草稿、发布）")

    @ExcelProperty(value = "state")
    private String state = "草稿";

}

