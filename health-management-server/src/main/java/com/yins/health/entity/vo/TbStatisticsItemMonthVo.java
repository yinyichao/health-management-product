package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ExcelIgnoreUnannotated
public class TbStatisticsItemMonthVo {
    @ApiModelProperty(value = "用户/部门")
    @ExcelProperty(value = "用户/部门")
    private String userName;

    @ApiModelProperty(value = "1月任务数")
    @ExcelProperty(value = "1月任务数")
    private Integer oneTasks = 0;

    @ApiModelProperty(value = "1月完成数")
    @ExcelProperty(value = "1月完成数")
    private Integer oneWorks = 0;

    @ApiModelProperty(value = "1月完成率")
    @ExcelProperty(value = "1月完成率")
    private String oneRate = "-";

    @ApiModelProperty(value = "2月任务数")
    @ExcelProperty(value = "2月任务数")
    private Integer twoTasks = 0;

    @ApiModelProperty(value = "2月完成数")
    @ExcelProperty(value = "2月完成数")
    private Integer twoWorks = 0;

    @ApiModelProperty(value = "2月完成率")
    @ExcelProperty(value = "2月完成率")
    private String twoRate = "-";

    @ApiModelProperty(value = "3月任务数")
    @ExcelProperty(value = "3月任务数")
    private Integer threeTasks = 0;

    @ApiModelProperty(value = "3月完成数")
    @ExcelProperty(value = "3月完成数")
    private Integer threeWorks = 0;

    @ApiModelProperty(value = "3月完成率")
    @ExcelProperty(value = "3月完成率")
    private String threeRate = "-";

    @ApiModelProperty(value = "4月任务数")
    @ExcelProperty(value = "4月任务数")
    private Integer fourTasks = 0;

    @ApiModelProperty(value = "4月完成数")
    @ExcelProperty(value = "4月完成数")
    private Integer fourWorks = 0;

    @ApiModelProperty(value = "4月完成率")
    @ExcelProperty(value = "4月完成率")
    private String fourRate = "-";

    @ApiModelProperty(value = "5月任务数")
    @ExcelProperty(value = "5月任务数")
    private Integer fiveTasks = 0;

    @ApiModelProperty(value = "5月完成数")
    @ExcelProperty(value = "5月完成数")
    private Integer fiveWorks = 0;

    @ApiModelProperty(value = "5月完成率")
    @ExcelProperty(value = "5月完成率")
    private String fiveRate = "-";

    @ApiModelProperty(value = "6月任务数")
    @ExcelProperty(value = "6月任务数")
    private Integer sixTasks = 0;

    @ApiModelProperty(value = "6月完成数")
    @ExcelProperty(value = "6月完成数")
    private Integer sixWorks = 0;

    @ApiModelProperty(value = "6月完成率")
    @ExcelProperty(value = "6月完成率")
    private String sixRate = "-";

    @ApiModelProperty(value = "7月任务数")
    @ExcelProperty(value = "7月任务数")
    private Integer sevenTasks = 0;

    @ApiModelProperty(value = "7月完成数")
    @ExcelProperty(value = "7月完成数")
    private Integer sevenWorks = 0;

    @ApiModelProperty(value = "7月完成率")
    @ExcelProperty(value = "7月完成率")
    private String sevenRate = "-";

    @ApiModelProperty(value = "8月任务数")
    @ExcelProperty(value = "8月任务数")
    private Integer eightTasks = 0;

    @ApiModelProperty(value = "8月完成数")
    @ExcelProperty(value = "8月完成数")
    private Integer eightWorks = 0;

    @ApiModelProperty(value = "8月完成率")
    @ExcelProperty(value = "8月完成率")
    private String eightRate = "-";

    @ApiModelProperty(value = "9月任务数")
    @ExcelProperty(value = "9月任务数")
    private Integer nineTasks = 0;

    @ApiModelProperty(value = "9月完成数")
    @ExcelProperty(value = "9月完成数")
    private Integer nineWorks = 0;

    @ApiModelProperty(value = "9月完成率")
    @ExcelProperty(value = "9月完成率")
    private String nineRate = "-";

    @ApiModelProperty(value = "10月任务数")
    @ExcelProperty(value = "10月任务数")
    private Integer tenTasks = 0;

    @ApiModelProperty(value = "10月完成数")
    @ExcelProperty(value = "10月完成数")
    private Integer tenWorks = 0;

    @ApiModelProperty(value = "10月完成率")
    @ExcelProperty(value = "10月完成率")
    private String tenRate = "-";

    @ApiModelProperty(value = "11月任务数")
    @ExcelProperty(value = "11月任务数")
    private Integer elevenTasks = 0;

    @ApiModelProperty(value = "11月完成数")
    @ExcelProperty(value = "11月完成数")
    private Integer elevenWorks = 0;

    @ApiModelProperty(value = "11月完成率")
    @ExcelProperty(value = "11月完成率")
    private String elevenRate = "-";

    @ApiModelProperty(value = "12月任务数")
    @ExcelProperty(value = "12月任务数")
    private Integer twelveTasks = 0;

    @ApiModelProperty(value = "12月完成数")
    @ExcelProperty(value = "12月完成数")
    private Integer twelveWorks = 0;

    @ApiModelProperty(value = "12月完成率")
    @ExcelProperty(value = "12月完成率")
    private String twelveRate = "-";

}
