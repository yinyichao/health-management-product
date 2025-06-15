package com.yins.health.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.YearMonth;
import java.util.*;

@Data
public class TbStatisticsItemMonthVoOld {
    @ApiModelProperty(value = "部门ID")
    private Integer deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;
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
    private Integer taskId;

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

    public static void month(TbStatisticsItemMonthVo vo,TbStatisticsItemMonthVoOld item,Integer type) {
        YearMonth ym = YearMonth.parse(item.getCycle());
        int month = ym.getMonthValue();
        switch (month) {
            case 1:
                if (type == 1) {
                    vo.setOneWorks(item.getViewsWorks());
                    vo.setOneTasks(item.getViewsTasks());
                    vo.setOneRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setOneWorks(item.getAddWorks());
                    vo.setOneTasks(item.getAddTasks());
                    vo.setOneRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setOneWorks(item.getQuestionnaireWorks());
                    vo.setOneTasks(item.getQuestionnaireTasks());
                    vo.setOneRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 2:
                if (type == 1) {
                    vo.setTwoWorks(item.getViewsWorks());
                    vo.setTwoTasks(item.getViewsTasks());
                    vo.setTwoRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setTwoWorks(item.getAddWorks());
                    vo.setTwoTasks(item.getAddTasks());
                    vo.setTwoRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setTwoWorks(item.getQuestionnaireWorks());
                    vo.setTwoTasks(item.getQuestionnaireTasks());
                    vo.setTwoRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 3:
                if (type == 1) {
                    vo.setThreeWorks(item.getViewsWorks());
                    vo.setThreeTasks(item.getViewsTasks());
                    vo.setThreeRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setThreeWorks(item.getAddWorks());
                    vo.setThreeTasks(item.getAddTasks());
                    vo.setThreeRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setThreeWorks(item.getQuestionnaireWorks());
                    vo.setThreeTasks(item.getQuestionnaireTasks());
                    vo.setThreeRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 4:
                if (type == 1) {
                    vo.setFourWorks(item.getViewsWorks());
                    vo.setFourTasks(item.getViewsTasks());
                    vo.setFourRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setFourWorks(item.getAddWorks());
                    vo.setFourTasks(item.getAddTasks());
                    vo.setFourRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setFourWorks(item.getQuestionnaireWorks());
                    vo.setFourTasks(item.getQuestionnaireTasks());
                    vo.setFourRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 5:
                if (type == 1) {
                    vo.setFiveWorks(item.getViewsWorks());
                    vo.setFiveTasks(item.getViewsTasks());
                    vo.setFiveRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setFiveWorks(item.getAddWorks());
                    vo.setFiveTasks(item.getAddTasks());
                    vo.setFiveRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setFiveWorks(item.getQuestionnaireWorks());
                    vo.setFiveTasks(item.getQuestionnaireTasks());
                    vo.setFiveRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 6:
                if (type == 1) {
                    vo.setSixWorks(item.getViewsWorks());
                    vo.setSixTasks(item.getViewsTasks());
                    vo.setSixRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setSixWorks(item.getAddWorks());
                    vo.setSixTasks(item.getAddTasks());
                    vo.setSixRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setSixWorks(item.getQuestionnaireWorks());
                    vo.setSixTasks(item.getQuestionnaireTasks());
                    vo.setSixRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 7:
                if (type == 1) {
                    vo.setSevenWorks(item.getViewsWorks());
                    vo.setSevenTasks(item.getViewsTasks());
                    vo.setSevenRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setSevenWorks(item.getAddWorks());
                    vo.setSevenTasks(item.getAddTasks());
                    vo.setSevenRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setSevenWorks(item.getQuestionnaireWorks());
                    vo.setSevenTasks(item.getQuestionnaireTasks());
                    vo.setSevenRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 8:
                if (type == 1) {
                    vo.setEightWorks(item.getViewsWorks());
                    vo.setEightTasks(item.getViewsTasks());
                    vo.setEightRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setEightWorks(item.getAddWorks());
                    vo.setEightTasks(item.getAddTasks());
                    vo.setEightRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setEightWorks(item.getQuestionnaireWorks());
                    vo.setEightTasks(item.getQuestionnaireTasks());
                    vo.setEightRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 9:
                if (type == 1) {
                    vo.setNineWorks(item.getViewsWorks());
                    vo.setNineTasks(item.getViewsTasks());
                    vo.setNineRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setNineWorks(item.getAddWorks());
                    vo.setNineTasks(item.getAddTasks());
                    vo.setNineRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setNineWorks(item.getQuestionnaireWorks());
                    vo.setNineTasks(item.getQuestionnaireTasks());
                    vo.setNineRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 10:
                if (type == 1) {
                    vo.setTenWorks(item.getViewsWorks());
                    vo.setTenTasks(item.getViewsTasks());
                    vo.setTenRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setTenWorks(item.getAddWorks());
                    vo.setTenTasks(item.getAddTasks());
                    vo.setTenRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setTenWorks(item.getQuestionnaireWorks());
                    vo.setTenTasks(item.getQuestionnaireTasks());
                    vo.setTenRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 11:
                if (type == 1) {
                    vo.setElevenWorks(item.getViewsWorks());
                    vo.setElevenTasks(item.getViewsTasks());
                    vo.setElevenRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setElevenWorks(item.getAddWorks());
                    vo.setElevenTasks(item.getAddTasks());
                    vo.setElevenRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setElevenWorks(item.getQuestionnaireWorks());
                    vo.setElevenTasks(item.getQuestionnaireTasks());
                    vo.setElevenRate(item.getQuestionnaireWorksRate());
                }
                break;
            case 12:
                if (type == 1) {
                    vo.setTwelveWorks(item.getViewsWorks());
                    vo.setTwelveTasks(item.getViewsTasks());
                    vo.setTwelveRate(item.getViewsWorksRate());
                } else if (type == 2) {
                    vo.setTwelveWorks(item.getAddWorks());
                    vo.setTwelveTasks(item.getAddTasks());
                    vo.setTwelveRate(item.getAddWorksRate());
                } else if (type == 3) {
                    vo.setTwelveWorks(item.getQuestionnaireWorks());
                    vo.setTwelveTasks(item.getQuestionnaireTasks());
                    vo.setTwelveRate(item.getQuestionnaireWorksRate());
                }
                break;
        }
    }
}
