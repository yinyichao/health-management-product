package com.yins.health.entity;

import java.util.Date;
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
@ApiModel("人才政策主表实体类")
@SuppressWarnings("serial")
@TableName("tb_talent_policy")
/**
 * 人才政策主表(TbTalentPolicy)表实体类
 *
 * @author yinyichao
 * @since 2025-05-13 11:17:56
 */
public class TbTalentPolicy extends Model<TbTalentPolicy> {
//政策ID
     @ApiModelProperty(value = "政策ID")

    @ExcelProperty(value = "id")
    private Long id;
//政策名称
     @ApiModelProperty(value = "政策名称")

    @ExcelProperty(value = "policyName")
    private String policyName;
//补贴金额
     @ApiModelProperty(value = "补贴金额")

    @ExcelProperty(value = "subsidyAmount")
    private String subsidyAmount;
//申请条件
     @ApiModelProperty(value = "申请条件")

    @ExcelProperty(value = "conditions")
    private String conditions;
//申请材料
     @ApiModelProperty(value = "申请材料")

    @ExcelProperty(value = "materials")
    private String materials;
//原文链接
     @ApiModelProperty(value = "原文链接")

    @ExcelProperty(value = "originalUrl")
    private String originalUrl;
//办理链接
     @ApiModelProperty(value = "办理链接")

    @ExcelProperty(value = "applyUrl")
    private String applyUrl;
//状态：1启用 0禁用
     @ApiModelProperty(value = "状态：1启用 0禁用")

    @ExcelProperty(value = "status")
    private Integer status;
//创建时间
     @ApiModelProperty(value = "创建时间")

    @ExcelProperty(value = "createdAt")
    private Date createdAt;
//更新时间
     @ApiModelProperty(value = "更新时间")

    @ExcelProperty(value = "updatedAt")
    private Date updatedAt;
//人才类型
     @ApiModelProperty(value = "人才类型")

    @ExcelProperty(value = "talentType")
    private Integer talentType;
//所属地区
     @ApiModelProperty(value = "所属地区")

    @ExcelProperty(value = "area")
    private String area;
//服务类型
     @ApiModelProperty(value = "服务类型")

    @ExcelProperty(value = "serviceType")
    private String serviceType;
//办事类型
     @ApiModelProperty(value = "办事类型")

    @ExcelProperty(value = "banshiType")
    private Integer banshiType;
//精简条件（申请条件）
     @ApiModelProperty(value = "精简条件（申请条件）")

    @ExcelProperty(value = "simplifiedConditions")
    private String simplifiedConditions;
//是否为深圳市
     @ApiModelProperty(value = "是否为深圳市")

    @ExcelProperty(value = "isShenzhen")
    private Integer isShenzhen;
//知识库名
     @ApiModelProperty(value = "知识库名")

    @ExcelProperty(value = "knowledgesetName")
    private String knowledgesetName;
//知识库id
     @ApiModelProperty(value = "知识库id")

    @ExcelProperty(value = "knowledgesetId")
    private String knowledgesetId;
//发布部门
     @ApiModelProperty(value = "发布部门")

    @ExcelProperty(value = "department")
    private String department;
//发布日期
     @ApiModelProperty(value = "发布日期")

    @ExcelProperty(value = "publishDate")
    private Date publishDate;
//政策有效期
     @ApiModelProperty(value = "政策有效期")

    @ExcelProperty(value = "policyValidityPeriod")
    private String policyValidityPeriod;
//AI注解
     @ApiModelProperty(value = "AI注解")

    @ExcelProperty(value = "aiAnnotation")
    private String aiAnnotation;
//办结时间
     @ApiModelProperty(value = "办结时间")

    @ExcelProperty(value = "completionTime")
    private String completionTime;
//政策原文来源
     @ApiModelProperty(value = "政策原文来源")

    @ExcelProperty(value = "resource")
    private String resource;
//办理时间(单位：月)
     @ApiModelProperty(value = "办理时间(单位：月)")

    @ExcelProperty(value = "processingTime")
    private Integer processingTime;
//政策解析
     @ApiModelProperty(value = "政策解析")

    @ExcelProperty(value = "policyAnalysis")
    private String policyAnalysis;

}

