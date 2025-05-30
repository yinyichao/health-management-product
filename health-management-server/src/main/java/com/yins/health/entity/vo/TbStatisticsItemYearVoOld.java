package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.List;

@Data
public class TbStatisticsItemYearVoOld {
    @ApiModelProperty(value = "用户名")
    @ExcelProperty(value = "userName")
    private String userName;

    @ApiModelProperty(value = "年")
    @ExcelProperty(value = "year")
    private String year;

    @ApiModelProperty(value = "0、日；1、周")
    @ExcelProperty(value = "type")
    private Integer type;

    @ApiModelProperty(value = "面见完成数")
    @ExcelProperty(value = "viewsNum")
    private Integer viewsNum;

    @ApiModelProperty(value = "增员完成数")
    @ExcelProperty(value = "addNum")
    private Integer addNum;

    @ApiModelProperty(value = "问卷完成数")
    @ExcelProperty(value = "questionnaireNum")
    private Integer questionnaireNum;

    @ApiModelProperty(value = "总天数")
    @ExcelProperty(value = "days")
    private Integer days = Year.of(Year.now().getValue()).isLeap() ? 366 : 365;

    @ApiModelProperty(value = "总周数")
    @ExcelProperty(value = "weeks")
    private Integer weeks = 35;

    @ApiModelProperty(value = "面见完成率")
    @ExcelProperty(value = "viewsWorksRate")
    private String viewsWorksRate;

    @ApiModelProperty(value = "增员完成率")
    @ExcelProperty(value = "addWorksRate")
    private String addWorksRate;

    @ApiModelProperty(value = "问卷完成率")
    @ExcelProperty(value = "questionnaireWorksRate")
    private String questionnaireWorksRate;

    public void setViewsWorksRate() {
        double percentage;
        if(type == 0){
            percentage = ((double) viewsNum / days) * 100;
        }else{
            percentage = ((double) viewsNum / weeks) * 100;
        }
        DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
        this.viewsWorksRate = df.format(percentage) + "%";
    }

    public void setAddWorksRate() {
        double percentage;
        if(type == 0){
            percentage = ((double) addNum / days) * 100;
        }else{
            percentage = ((double) addNum / weeks) * 100;
        }
        DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
        this.addWorksRate = df.format(percentage) + "%";
    }

    public void setQuestionnaireWorksRate() {
        double percentage;
        if(type == 0){
            percentage = ((double) questionnaireNum / days) * 100;
        }else{
            percentage = ((double) questionnaireNum / weeks) * 100;
        }
        DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
        this.questionnaireWorksRate = df.format(percentage) + "%";
    }
    public static void change(List<TbStatisticsItemYearVoOld> list) {
        for (TbStatisticsItemYearVoOld item : list) {
            item.setViewsWorksRate();
            item.setAddWorksRate();
            item.setQuestionnaireWorksRate();
        }
    }
}
