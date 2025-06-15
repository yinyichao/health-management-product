package com.yins.health.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@NoArgsConstructor
@ApiModel("风险规则表;实体类")
@SuppressWarnings("serial")
@TableName("tb_rule")
/**
 * 风险规则表;(TbRule)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:35
 */
public class TbRule extends Model<TbRule> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//名称
     @ApiModelProperty(value = "名称")

    @ExcelProperty(value = "name")
    private String name;
//规则（一般、特殊）
     @ApiModelProperty(value = "规则（一般、特殊）")

    @ExcelProperty(value = "rule")
    private String rule;
//规则类型（高风险、中风险、低风险）
     @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")

    @ExcelProperty(value = "type")
    private String type;
//周期（月、周、日、小时）
    @ApiModelProperty(value = "周期（月、周、日、小时）")

    @ExcelProperty(value = "cycle")
    private String cycle;
//次数
     @ApiModelProperty(value = "次数")

    @ExcelProperty(value = "num")
    private Integer num;
    //次数
    @ApiModelProperty(value = "小时数")

    @ExcelProperty(value = "hours")
    private Integer hours;
//状态（删除）
     @ApiModelProperty(value = "状态（0、启用，1、禁用）")

    @ExcelProperty(value = "state")
    private Integer state;
//采集、面见、增员
     @ApiModelProperty(value = "采集、面见、增员")

    @ExcelProperty(value = "ruleType")
    private String ruleType;
//创建人
     @ApiModelProperty(value = "创建人")

    @ExcelProperty(value = "createdUser")
    private String createdUser;
//创建时间
     @ApiModelProperty(value = "创建时间")
     @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "createdTime")
    private Date createdTime;
    //更新人
     @ApiModelProperty(value = "更新人")

    @ExcelProperty(value = "updatedUser")
    private String updatedUser;
    //更新时间
     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "updatedTime")
    private Date updatedTime;
    //状态（删除）
    @ApiModelProperty(value = "删除字段（0、未删，1、删除）")

    @ExcelProperty(value = "del")
    private Integer del;
    //周期（月、周、日、小时）
    @ApiModelProperty(value = "内容（拼接）")

    @ExcelProperty(value = "content")
    private String content;

    public void change(){
        if(rule == null || rule.isEmpty() || rule.equals("一般规则")){
            rule = "一般规则";
            content = hours == null ? "每" + cycle + "提交超过" +num+ "次时标记为"+ruleType : "每"+hours + "小时提交超过" +num+ "次时标记为"+ruleType;
        }else{
            content = "每" + hours + cycle+ "内，同一客户提交"+num +"次时标记为"+ruleType;
        }
    }
}

