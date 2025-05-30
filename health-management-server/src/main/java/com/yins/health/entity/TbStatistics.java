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
@ApiModel("绩效表")
@SuppressWarnings("serial")
@TableName("tb_statistics")
/**
 * (TbStatistics)表实体类
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
public class TbStatistics extends Model<TbStatistics> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//年
     @ApiModelProperty(value = "年")

    @ExcelProperty(value = "year")
    private String year;
//用户ID
     @ApiModelProperty(value = "用户ID")

    @ExcelProperty(value = "userId")
    private Integer userId;
//用户名
     @ApiModelProperty(value = "用户名")

    @ExcelProperty(value = "userName")
    private String userName;
//删除（0、启用，1、删除）
     @ApiModelProperty(value = "删除（0、启用，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;

    //任务ID
    @ApiModelProperty(value = "任务ID")

    @ExcelProperty(value = "taskId")
    private Integer taskId;
}

