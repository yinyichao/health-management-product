package com.yins.health.entity.dto;

import com.yins.health.query.Query;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbAddDto extends Query {
    @ApiModelProperty(value = "应聘者")
    private String name;
    @ApiModelProperty(value = "面试者")
    private String username;
    @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")
    private String label;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
