package com.yins.health.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel("贷款产品表实体类")
@SuppressWarnings("serial")
@TableName("tb_loan_products")
/**
 * 贷款产品表(TbLoanProducts)表实体类
 *
 * @author yinyichao
 * @since 2025-05-13 11:31:17
 */
public class TbLoanProducts extends Model<TbLoanProducts> {
//主键ID
     @ApiModelProperty(value = "主键ID")

    @ExcelProperty(value = "id")
    private Integer id;
//银行名称
     @ApiModelProperty(value = "银行名称")

    @ExcelProperty(value = "bankName")
    private String bankName;
//贷款产品名称
     @ApiModelProperty(value = "贷款产品名称")

    @ExcelProperty(value = "productName")
    private String productName;
//贷款利率（%）
     @ApiModelProperty(value = "贷款利率（%）")

    @ExcelProperty(value = "interestRate")
    private String interestRate;
//贷款额度（元）
     @ApiModelProperty(value = "贷款额度（元）")

    @ExcelProperty(value = "loanAmount")
    private String loanAmount;
//分类
     @ApiModelProperty(value = "分类")

    @ExcelProperty(value = "category")
    private String category;
//贷款类型
     @ApiModelProperty(value = "贷款类型")

    @ExcelProperty(value = "loanType")
    private String loanType;
//ai注解
     @ApiModelProperty(value = "ai注解")

    @ExcelProperty(value = "aiAnnotate")
    private String aiAnnotate;
//申请条件精简
     @ApiModelProperty(value = "申请条件精简")

    @ExcelProperty(value = "simplifiedCondition")
    private String simplifiedCondition;
//准入条件
     @ApiModelProperty(value = "准入条件")

    @ExcelProperty(value = "condition")
     @TableField("`condition`")
    private String condition;
//材料
     @ApiModelProperty(value = "材料")

    @ExcelProperty(value = "material")
    private String material;
//产品特色
     @ApiModelProperty(value = "产品特色")

    @ExcelProperty(value = "feature")
    private String feature;
//还款方式
     @ApiModelProperty(value = "还款方式")

    @ExcelProperty(value = "repaymentMethod")
    private String repaymentMethod;
//对象
     @ApiModelProperty(value = "对象")

    @ExcelProperty(value = "object")
    private String object;
//还款期限
     @ApiModelProperty(value = "还款期限")

    @ExcelProperty(value = "repaymentPeriod")
    private String repaymentPeriod;
//链接
     @ApiModelProperty(value = "链接")

    @ExcelProperty(value = "url")
    private String url;

}

