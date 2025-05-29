package com.yins.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
@Data
@NoArgsConstructor
@ApiModel("")
@SuppressWarnings("serial")
@TableName("tb_statistics_item")
/**
 * (TbStatisticsItem)表实体类
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
public class TbStatisticsItem extends Model<TbStatisticsItem> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//用户ID
     @ApiModelProperty(value = "用户ID")

    @ExcelProperty(value = "userId")
    private Integer userId;
//用户名
     @ApiModelProperty(value = "用户名")

    @ExcelProperty(value = "userName")
    private String userName;
//周期（年-月-日【周】）
     @ApiModelProperty(value = "周期（年-月-日【周】）")

    @ExcelProperty(value = "cycle")
    private String cycle;
    @ApiModelProperty(value = "年")

    @ExcelProperty(value = "year")
    private Integer year;
//0、日；1、周；2、月
     @ApiModelProperty(value = "0、日；1、周；2、月")

    @ExcelProperty(value = "type")
    private Integer type;
//面见任务数
     @ApiModelProperty(value = "面见任务数")

    @ExcelProperty(value = "viewsTasks")
    private Integer viewsTasks;
//增员任务数
     @ApiModelProperty(value = "增员任务数")

    @ExcelProperty(value = "addTasks")
    private Integer addTasks;
//问卷任务数
     @ApiModelProperty(value = "问卷任务数")

    @ExcelProperty(value = "questionnaireTasks")
    private Integer questionnaireTasks;
//面见完成数
     @ApiModelProperty(value = "面见完成数")

    @ExcelProperty(value = "viewsWorks")
    private Integer viewsWorks;
//增员完成数
     @ApiModelProperty(value = "增员完成数")

    @ExcelProperty(value = "addWorks")
    private Integer addWorks;
//问卷完成数
     @ApiModelProperty(value = "问卷完成数")

    @ExcelProperty(value = "questionnaireWorks")
    private Integer questionnaireWorks;

}

