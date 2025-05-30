package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.List;

@Data
@ExcelIgnoreUnannotated
public class TbStatisticsItemYearVo {
    @ApiModelProperty(value = "用户/部门")
    @ExcelProperty(value = "用户/部门")
    private String userName;

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "（天）完成数")
    private Integer dayNum;

    @ApiModelProperty(value = "（天/总天数）完成数")
    @ExcelProperty(value = "任务完成天数")
    private String dayNumStr;

    @ApiModelProperty(value = "每日任务完成率")
    @ExcelProperty(value = "每日任务完成率")
    private String dayRate;

    @ApiModelProperty(value = "（周）完成数")
    private Integer weekNum;

    @ApiModelProperty(value = "（周/总周数）完成数")
    @ExcelProperty(value = "任务完成周数")
    private String weekNumStr;

    @ApiModelProperty(value = "每周任务完成率")
    @ExcelProperty(value = "每周任务完成率")
    private String weekRate;

    @ApiModelProperty(value = "总天数")
    private Integer days = Year.of(Year.now().getValue()).isLeap() ? 366 : 365;

    @ApiModelProperty(value = "总周数")
    private Integer weeks = 35;

    public void setViewsWorksRate() {
        double percentageDay = ((double) dayNum / days) * 100;
        double percentageWeek = ((double) weekNum / weeks) * 100;
        DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
        this.dayRate = df.format(percentageDay) + "%";
        this.weekRate = df.format(percentageWeek) + "%";
        this.dayNumStr = dayNum + "/" + days;
        this.weekNumStr = weekNum + "/" + weeks;
    }


    public static void change(List<TbStatisticsItemYearVo> list) {
        for (TbStatisticsItemYearVo item : list) {
            item.setViewsWorksRate();
        }
    }
}
