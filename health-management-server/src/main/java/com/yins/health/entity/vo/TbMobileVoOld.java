package com.yins.health.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TbMobileVoOld {
    @ApiModelProperty(value = "每日面见任务数")
    private Integer dayViewsTasks;
    @ApiModelProperty(value = "每日面见完成数")
    private Integer dayViewsWorks;

    @ApiModelProperty(value = "每日增员任务数")
    private Integer dayAddTasks;
    @ApiModelProperty(value = "每日增员完成数")
    private Integer dayAddWorks;

    @ApiModelProperty(value = "每日问卷任务数")
    private Integer dayQuestionnaireTasks;
    @ApiModelProperty(value = "每日问卷完成数")
    private Integer dayQuestionnaireWorks;

    @ApiModelProperty(value = "每周面见任务数")
    private Integer weekViewsTasks;
    @ApiModelProperty(value = "每周面见完成数")
    private Integer weekViewsWorks;

    @ApiModelProperty(value = "每周增员任务数")
    private Integer weekAddTasks;
    @ApiModelProperty(value = "每周增员完成数")
    private Integer weekAddWorks;

    @ApiModelProperty(value = "每周问卷任务数")
    private Integer weekQuestionnaireTasks;
    @ApiModelProperty(value = "每周问卷完成数")
    private Integer weekQuestionnaireWorks;

    @ApiModelProperty(value = "每月面见任务数")
    private Integer monthViewsTasks;
    @ApiModelProperty(value = "每月面见完成数")
    private Integer monthViewsWorks;

    @ApiModelProperty(value = "每月增员任务数")
    private Integer monthAddTasks;
    @ApiModelProperty(value = "每月增员完成数")
    private Integer monthAddWorks;

    @ApiModelProperty(value = "每月问卷任务数")
    private Integer monthQuestionnaireTasks;
    @ApiModelProperty(value = "每月问卷完成数")
    private Integer monthQuestionnaireWorks;
}
