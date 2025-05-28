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
@ApiModel("风险规则命中表实体类")
@SuppressWarnings("serial")
@TableName("tb_rule_model")
/**
 * 风险规则命中表(TbRuleModel)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 17:09:03
 */
public class TbRuleModel extends Model<TbRuleModel> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.INPUT)
    @ExcelProperty(value = "id")
    private Integer id;
//规则ID
     @ApiModelProperty(value = "规则ID")

    @ExcelProperty(value = "ruleId")
    private Integer ruleId;
//命中事件ID
     @ApiModelProperty(value = "命中事件ID")

    @ExcelProperty(value = "modelId")
    private Integer modelId;
//创建时间
     @ApiModelProperty(value = "创建时间")
     @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "created_time")
    private Date createdTime;

}

