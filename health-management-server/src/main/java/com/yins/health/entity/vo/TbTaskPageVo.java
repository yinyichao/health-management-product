package com.yins.health.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class TbTaskPageVo{
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "实用人员数")
    private String num;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
}
