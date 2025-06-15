package com.yins.health.entity.dto;

import com.yins.health.query.Query;
import com.yins.health.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbViewDto extends Query {
    @ApiModelProperty(value = "拜访者/陪访者")
    private String visitor;
    @ApiModelProperty(value = "被拜访者")
    private String visitedPerson;
    @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")
    private String label;
    @ApiModelProperty(value = "状态（作废、有效）")
    private String state;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    // 自动拼接后的开始时间
    public String getBeginDateTime() {
        return StringUtils.hasText(beginTime) ? beginTime + " 00:00:00" : null;
    }

    // 自动拼接后的结束时间
    public String getEndDateTime() {
        return StringUtils.hasText(endTime) ? endTime + " 23:59:59" : null;
    }
}
