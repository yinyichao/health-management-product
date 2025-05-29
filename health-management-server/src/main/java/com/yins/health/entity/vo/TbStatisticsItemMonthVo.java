package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DecimalFormat;
import java.util.List;

@Data
public class TbStatisticsItemMonthVo {
    @ApiModelProperty(value = "用户名")
    @ExcelProperty(value = "userName")
    private String userName;

    @ApiModelProperty(value = "2025年1月")
    @ExcelProperty(value = "cycle")
    private String cycle;

    @ApiModelProperty(value = "面见任务数")
    @ExcelProperty(value = "viewsTasks")
    private Integer viewsTasks;

    @ApiModelProperty(value = "增员任务数")
    @ExcelProperty(value = "addTasks")
    private Integer addTasks;

    @ApiModelProperty(value = "问卷任务数")
    @ExcelProperty(value = "questionnaireTasks")
    private Integer questionnaireTasks;

    @ApiModelProperty(value = "面见完成数")
    @ExcelProperty(value = "viewsWorks")
    private Integer viewsWorks;

    @ApiModelProperty(value = "增员完成数")
    @ExcelProperty(value = "addWorks")
    private Integer addWorks;

    @ApiModelProperty(value = "问卷完成数")
    @ExcelProperty(value = "questionnaireWorks")
    private Integer questionnaireWorks;

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
        if(this.viewsTasks == 0 || this.viewsWorks == 0){
            this.viewsWorksRate = "-";
        }else{
            double percentage = ((double) viewsWorks / viewsTasks) * 100;
            DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
            this.viewsWorksRate = df.format(percentage) + "%";
        }
    }

    public void setAddWorksRate() {
        if(this.addTasks == 0 || this.addWorks == 0){
            this.addWorksRate = "-";
        }else{
            double percentage = ((double) addWorks / addTasks) * 100;
            DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
            this.addWorksRate = df.format(percentage) + "%";
        }
    }

    public void setQuestionnaireWorksRate() {
        if(this.questionnaireTasks == 0 || this.questionnaireWorks == 0){
            this.questionnaireWorksRate = "-";
        }else{
            double percentage = ((double) questionnaireWorks / questionnaireTasks) * 100;
            DecimalFormat df = new DecimalFormat("0.00"); // 保留两位小数
            this.questionnaireWorksRate = df.format(percentage) + "%";
        }
    }
    public static void change(List<TbStatisticsItemMonthVo> list) {
        for (TbStatisticsItemMonthVo item : list) {
            item.setViewsWorksRate();
            item.setAddWorksRate();
            item.setQuestionnaireWorksRate();
        }
    }
}
