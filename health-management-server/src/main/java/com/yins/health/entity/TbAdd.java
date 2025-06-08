package com.yins.health.entity;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@NoArgsConstructor
@ApiModel("增员管理表;实体类")
@SuppressWarnings("serial")
@TableName("tb_add")
@ExcelIgnoreUnannotated
/**
 * 增员管理表;(TbAdd)表实体类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public class TbAdd extends Model<TbAdd> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    private Integer id;

    //面试者
    @ApiModelProperty(value = "面试官")
    @ExcelProperty(value = "面试官")
    private String username;

     @ApiModelProperty(value = "应聘者")
    @ExcelProperty(value = "应聘者")
    private String name;

     @ApiModelProperty(value = "电话")
    @ExcelProperty(value = "应聘者联系方式")
    private String telephone;

    @ApiModelProperty(value = "规则类型（高风险、中风险、低风险）")
    @ExcelProperty(value = "风险类型")
    private String label;

    @ApiModelProperty(value = "风险说明")
    @ExcelProperty(value = "风险说明")
    private String labelContent;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "提交时间")
    private Date createdTime;

    @ApiModelProperty(value = "状态（作废、有效）")
    @ExcelProperty(value = "状态")
    private String state = "有效";


     @ApiModelProperty(value = "备注")
    private String remark;
//面试地点
     @ApiModelProperty(value = "面试地点")
    private String location;
//图片1
     @ApiModelProperty(value = "图片1")
    private String pic1;
//图片2
     @ApiModelProperty(value = "图片2")
    private String pic2;
//图片3
     @ApiModelProperty(value = "图片3")
    private String pic3;
//应聘登记表
     @ApiModelProperty(value = "应聘登记表")
    private String registration;

     @ApiModelProperty(value = "更新人")
    private String updatedUser;
//更新时间
     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
//规则类型（高风险、中风险、低风险）

//删除字段（0、未删，1、删除）
     @ApiModelProperty(value = "删除字段（0、未删，1、删除）")
    private Integer del;

    @ApiModelProperty(value = "创建人")
    private String createdUser;

}

