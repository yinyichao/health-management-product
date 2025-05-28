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
@ApiModel("任务表;实体类")
@SuppressWarnings("serial")
@TableName("tb_task")
/**
 * 任务表;(TbTask)表实体类
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
public class TbTask extends Model<TbTask> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//名称
     @ApiModelProperty(value = "名称")

    @ExcelProperty(value = "name")
    private String name;
//年度
     @ApiModelProperty(value = "年度")

    @ExcelProperty(value = "year")
    private String year;
//每日面见任务数
     @ApiModelProperty(value = "每日面见任务数")

    @ExcelProperty(value = "dayViewsTasks")
    private Integer dayViewsTasks;
//每日增员任务数
     @ApiModelProperty(value = "每日增员任务数")

    @ExcelProperty(value = "dayAddTasks")
    private Integer dayAddTasks;
//每日问卷任务数
     @ApiModelProperty(value = "每日问卷任务数")

    @ExcelProperty(value = "dayQuestionnaireTasks")
    private Integer dayQuestionnaireTasks;
//每周面见任务数
     @ApiModelProperty(value = "每周面见任务数")

    @ExcelProperty(value = "weekViewsTasks")
    private Integer weekViewsTasks;
//每周增员任务数
     @ApiModelProperty(value = "每周增员任务数")

    @ExcelProperty(value = "weekAddTasks")
    private Integer weekAddTasks;
//每周问卷任务数
     @ApiModelProperty(value = "每周问卷任务数")

    @ExcelProperty(value = "weekQuestionnaireTasks")
    private Integer weekQuestionnaireTasks;
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
//一月面见任务数
     @ApiModelProperty(value = "一月面见任务数")

    @ExcelProperty(value = "oneViewsTasks")
    private Integer oneViewsTasks;
//一月增员任务数
     @ApiModelProperty(value = "一月增员任务数")

    @ExcelProperty(value = "oneAddTasks")
    private Integer oneAddTasks;
//一月问卷任务数
     @ApiModelProperty(value = "一月问卷任务数")

    @ExcelProperty(value = "oneQuestionnaireTasks")
    private Integer oneQuestionnaireTasks;
//二月面见任务数
     @ApiModelProperty(value = "二月面见任务数")

    @ExcelProperty(value = "twoViewsTasks")
    private Integer twoViewsTasks;
//二月增员任务数
     @ApiModelProperty(value = "二月增员任务数")

    @ExcelProperty(value = "twoAddTasks")
    private Integer twoAddTasks;
//二月问卷任务数
     @ApiModelProperty(value = "二月问卷任务数")

    @ExcelProperty(value = "twoQuestionnaireTasks")
    private Integer twoQuestionnaireTasks;
//三月面见任务数
     @ApiModelProperty(value = "三月面见任务数")

    @ExcelProperty(value = "threeViewsTasks")
    private Integer threeViewsTasks;
//三月增员任务数
     @ApiModelProperty(value = "三月增员任务数")

    @ExcelProperty(value = "threeAddTasks")
    private Integer threeAddTasks;
//三月问卷任务数
     @ApiModelProperty(value = "三月问卷任务数")

    @ExcelProperty(value = "threeQuestionnaireTasks")
    private Integer threeQuestionnaireTasks;
//四月面见任务数
     @ApiModelProperty(value = "四月面见任务数")

    @ExcelProperty(value = "fourViewsTasks")
    private Integer fourViewsTasks;
//四月增员任务数
     @ApiModelProperty(value = "四月增员任务数")

    @ExcelProperty(value = "fourAddTasks")
    private Integer fourAddTasks;
//四月问卷任务数
     @ApiModelProperty(value = "四月问卷任务数")

    @ExcelProperty(value = "fourQuestionnaireTasks")
    private Integer fourQuestionnaireTasks;
//五月面见任务数
     @ApiModelProperty(value = "五月面见任务数")

    @ExcelProperty(value = "fiveViewsTasks")
    private Integer fiveViewsTasks;
//五月增员任务数
     @ApiModelProperty(value = "五月增员任务数")

    @ExcelProperty(value = "fiveAddTasks")
    private Integer fiveAddTasks;
//五月问卷任务数
     @ApiModelProperty(value = "五月问卷任务数")

    @ExcelProperty(value = "fiveQuestionnaireTasks")
    private Integer fiveQuestionnaireTasks;
//六月面见任务数
     @ApiModelProperty(value = "六月面见任务数")

    @ExcelProperty(value = "sixViewsTasks")
    private Integer sixViewsTasks;
//六月增员任务数
     @ApiModelProperty(value = "六月增员任务数")

    @ExcelProperty(value = "sixAddTasks")
    private Integer sixAddTasks;
//六月问卷任务数
     @ApiModelProperty(value = "六月问卷任务数")

    @ExcelProperty(value = "sixQuestionnaireTasks")
    private Integer sixQuestionnaireTasks;
//七月面见任务数
     @ApiModelProperty(value = "七月面见任务数")

    @ExcelProperty(value = "sevenViewsTasks")
    private Integer sevenViewsTasks;
//七月增员任务数
     @ApiModelProperty(value = "七月增员任务数")

    @ExcelProperty(value = "sevenAddTasks")
    private Integer sevenAddTasks;
//七月问卷任务数
     @ApiModelProperty(value = "七月问卷任务数")

    @ExcelProperty(value = "sevenQuestionnaireTasks")
    private Integer sevenQuestionnaireTasks;
//八月面见任务数
     @ApiModelProperty(value = "八月面见任务数")

    @ExcelProperty(value = "eightViewsTasks")
    private Integer eightViewsTasks;
//八月增员任务数
     @ApiModelProperty(value = "八月增员任务数")

    @ExcelProperty(value = "eightAddTasks")
    private Integer eightAddTasks;
//八月问卷任务数
     @ApiModelProperty(value = "八月问卷任务数")

    @ExcelProperty(value = "eightQuestionnaireTasks")
    private Integer eightQuestionnaireTasks;
//九月面见任务数
     @ApiModelProperty(value = "九月面见任务数")

    @ExcelProperty(value = "nineViewsTasks")
    private Integer nineViewsTasks;
//九月增员任务数
     @ApiModelProperty(value = "九月增员任务数")

    @ExcelProperty(value = "nineAddTasks")
    private Integer nineAddTasks;
//九月问卷任务数
     @ApiModelProperty(value = "九月问卷任务数")

    @ExcelProperty(value = "nineQuestionnaireTasks")
    private Integer nineQuestionnaireTasks;
//十月面见任务数
     @ApiModelProperty(value = "十月面见任务数")

    @ExcelProperty(value = "tenViewsTasks")
    private Integer tenViewsTasks;
//十月增员任务数
     @ApiModelProperty(value = "十月增员任务数")

    @ExcelProperty(value = "tenAddTasks")
    private Integer tenAddTasks;
//十月问卷任务数
     @ApiModelProperty(value = "十月问卷任务数")

    @ExcelProperty(value = "tenQuestionnaireTasks")
    private Integer tenQuestionnaireTasks;
//十一月面见任务数
     @ApiModelProperty(value = "十一月面见任务数")

    @ExcelProperty(value = "elevenViewsTasks")
    private Integer elevenViewsTasks;
//十一月增员任务数
     @ApiModelProperty(value = "十一月增员任务数")

    @ExcelProperty(value = "elevenAddTasks")
    private Integer elevenAddTasks;
//十一月问卷任务数
     @ApiModelProperty(value = "十一月问卷任务数")

    @ExcelProperty(value = "elevenQuestionnaireTasks")
    private Integer elevenQuestionnaireTasks;
//十二月面见任务数
     @ApiModelProperty(value = "十二月面见任务数")

    @ExcelProperty(value = "twelveViewsTasks")
    private Integer twelveViewsTasks;
//十二月增员任务数
     @ApiModelProperty(value = "十二月增员任务数")

    @ExcelProperty(value = "twelveAddTasks")
    private Integer twelveAddTasks;
//十二月问卷任务数
     @ApiModelProperty(value = "十二月问卷任务数")

    @ExcelProperty(value = "twelveQuestionnaireTasks")
    private Integer twelveQuestionnaireTasks;

//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;

}

