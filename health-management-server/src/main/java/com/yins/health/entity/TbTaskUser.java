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
@ApiModel("任务用户关联表;实体类")
@SuppressWarnings("serial")
@TableName("tb_task_user")
/**
 * 任务用户关联表;(TbTaskUser)表实体类
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
public class TbTaskUser extends Model<TbTaskUser> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//用户表ID
     @ApiModelProperty(value = "用户表ID")

    @ExcelProperty(value = "userId")
    private Integer userId;
//任务表ID
     @ApiModelProperty(value = "任务表ID")

    @ExcelProperty(value = "taskId")
    private Integer taskId;

}

