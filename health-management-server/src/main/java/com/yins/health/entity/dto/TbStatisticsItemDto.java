package com.yins.health.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbStatisticsItemDto {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "年")
    private String year;
    @ApiModelProperty(value = "1、面见；2、增员；3、问卷")
    @NonNull
    private Integer type;
}
