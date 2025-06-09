package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.yins.health.entity.TbStatisticsItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class TbStatisticsItemVo extends TbStatisticsItem {
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
