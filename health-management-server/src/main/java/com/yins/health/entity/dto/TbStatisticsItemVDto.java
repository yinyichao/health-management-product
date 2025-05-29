package com.yins.health.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbStatisticsItemVDto {
    @ApiModelProperty(value = "完成数量")
    private Integer works;
    @ApiModelProperty(value = "年月日")
    private String time;
}
