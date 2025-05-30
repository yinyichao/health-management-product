package com.yins.health.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TbRuleVo {
    @ApiModelProperty(value = "风险规则ID")
    private Integer id;
    @ApiModelProperty(value = "风险规则名称")
    private String name;
    @ApiModelProperty(value = "风险规则内容")
    private String content;
    @ApiModelProperty(value = "状态（0、启用，1、禁用）")
    private Integer state;
    @ApiModelProperty(value = "采集、面见、增员")
    private String ruleType;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "布控次数")
    private Integer controlNum;
}
