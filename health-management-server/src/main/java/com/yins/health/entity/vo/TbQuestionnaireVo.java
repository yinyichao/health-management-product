package com.yins.health.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TbQuestionnaireVo {
    @ApiModelProperty(value = "问卷ID")
    private Integer id;
    @ApiModelProperty(value = "问卷名称")
    private String name;
    @ApiModelProperty(value = "问卷编号")
    private String ref;
    @ApiModelProperty(value = "状态（草稿、发布）")
    private String state;
    @ApiModelProperty(value = "填写时间")
    private Date createdTime;
    @ApiModelProperty(value = "填写数")
    private Integer num;
}
