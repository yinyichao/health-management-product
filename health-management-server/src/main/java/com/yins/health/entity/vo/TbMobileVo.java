package com.yins.health.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TbMobileVo {
    @ApiModelProperty(value = "类型（面见/陪访、客户信息采集、增员面试）")
    private String type;
    @ApiModelProperty(value = "面见任务数")
    private Integer dayTasks;
    @ApiModelProperty(value = "面见完成数")
    private Integer dayWorks;

    @ApiModelProperty(value = "增员任务数")
    private Integer weekTasks;
    @ApiModelProperty(value = "增员完成数")
    private Integer weekWorks;

    @ApiModelProperty(value = "问卷任务数")
    private Integer monthTasks;
    @ApiModelProperty(value = "问卷完成数")
    private Integer monthWorks;

}
