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
@ApiModel("空间用房实体类")
@SuppressWarnings("serial")
@TableName("tb_space_hse_f")
/**
 * 空间用房(TbSpaceHseF)表实体类
 *
 * @author yinyichao
 * @since 2025-05-13 10:51:28
 */
public class TbSpaceHseF extends Model<TbSpaceHseF> {
//序号
     @ApiModelProperty(value = "序号")

    @ExcelProperty(value = "id")
    private Integer id;
//所属区域
     @ApiModelProperty(value = "所属区域")

    @ExcelProperty(value = "areaName")
    private String areaName;
//园区名称
     @ApiModelProperty(value = "园区名称")

    @ExcelProperty(value = "parkName")
    private String parkName;
//环境图片
     @ApiModelProperty(value = "环境图片")

    @ExcelProperty(value = "envtPicture")
    private String envtPicture;
//标签
     @ApiModelProperty(value = "标签")

    @ExcelProperty(value = "tagName")
    private String tagName;
//所属类别
     @ApiModelProperty(value = "所属类别")

    @ExcelProperty(value = "typeName")
    private String typeName;
//产业用房建筑面积（平方米）
     @ApiModelProperty(value = "产业用房建筑面积（平方米）")

    @ExcelProperty(value = "constrArea")
    private Double constrArea;
//租金参考价格（元/㎡·月）
     @ApiModelProperty(value = "租金参考价格（元/㎡·月）")

    @ExcelProperty(value = "referPrice")
    private Double referPrice;
//出售性质
     @ApiModelProperty(value = "出售性质")

    @ExcelProperty(value = "saleNature")
    private String saleNature;
//主要用途
     @ApiModelProperty(value = "主要用途")

    @ExcelProperty(value = "mainUse")
    private String mainUse;
//街道信息
     @ApiModelProperty(value = "街道信息")

    @ExcelProperty(value = "strName")
    private String strName;
//主干路
     @ApiModelProperty(value = "主干路")

    @ExcelProperty(value = "mainRoad")
    private String mainRoad;
//具体位置
     @ApiModelProperty(value = "具体位置")

    @ExcelProperty(value = "specificAddr")
    private String specificAddr;
//轨道交通
     @ApiModelProperty(value = "轨道交通")

    @ExcelProperty(value = "railTraffic")
    private String railTraffic;
//经度纬度
     @ApiModelProperty(value = "经度纬度")

    @ExcelProperty(value = "longLat")
    private String longLat;
//产权主体
     @ApiModelProperty(value = "产权主体")

    @ExcelProperty(value = "property")
    private String property;
//招商联系人
     @ApiModelProperty(value = "招商联系人")

    @ExcelProperty(value = "investPerson")
    private String investPerson;
//联系方式
     @ApiModelProperty(value = "联系方式")

    @ExcelProperty(value = "investPhone")
    private String investPhone;
//申请条件
     @ApiModelProperty(value = "申请条件")

    @ExcelProperty(value = "applyCondition")
    private String applyCondition;
//物业管理费（元/㎡·月）
     @ApiModelProperty(value = "物业管理费（元/㎡·月）")

    @ExcelProperty(value = "manageFee")
    private String manageFee;
//水电费
     @ApiModelProperty(value = "水电费")

    @ExcelProperty(value = "utilityFee")
    private Double utilityFee;
//标准层层高（m）
     @ApiModelProperty(value = "标准层层高（m）")

    @ExcelProperty(value = "layerHeight")
    private String layerHeight;
//标准层承重（KN/㎡）
     @ApiModelProperty(value = "标准层承重（KN/㎡）")

    @ExcelProperty(value = "layerWeight")
    private Double layerWeight;
//项目竣工时间
     @ApiModelProperty(value = "项目竣工时间")

    @ExcelProperty(value = "endTime")
    private String endTime;
//政策补助
     @ApiModelProperty(value = "政策补助")

    @ExcelProperty(value = "policySubsidy")
    private String policySubsidy;
//物业优惠
     @ApiModelProperty(value = "物业优惠")

    @ExcelProperty(value = "discounts")
    private String discounts;
//占地面积（㎡）
     @ApiModelProperty(value = "占地面积（㎡）")

    @ExcelProperty(value = "coverArea")
    private Double coverArea;
//项目产业定位
     @ApiModelProperty(value = "项目产业定位")

    @ExcelProperty(value = "projectTarget")
    private String projectTarget;
//配套食堂
     @ApiModelProperty(value = "配套食堂")

    @ExcelProperty(value = "canteen")
    private String canteen;
//中央空调
     @ApiModelProperty(value = "中央空调")

    @ExcelProperty(value = "airConditioner")
    private String airConditioner;
//娱乐休闲场所
     @ApiModelProperty(value = "娱乐休闲场所")

    @ExcelProperty(value = "leisurePlace")
    private String leisurePlace;
//配套宿舍
     @ApiModelProperty(value = "配套宿舍")

    @ExcelProperty(value = "dormitory")
    private String dormitory;
//消防设施
     @ApiModelProperty(value = "消防设施")

    @ExcelProperty(value = "fireFacility")
    private String fireFacility;
//客梯数量
     @ApiModelProperty(value = "客梯数量")

    @ExcelProperty(value = "passengerNum")
    private String passengerNum;
//客梯载重量（kg）
     @ApiModelProperty(value = "客梯载重量（kg）")

    @ExcelProperty(value = "passengerLoad")
    private Double passengerLoad;
//配电量（KVA）
     @ApiModelProperty(value = "配电量（KVA）")

    @ExcelProperty(value = "elecQuantity")
    private String elecQuantity;
//货梯数量
     @ApiModelProperty(value = "货梯数量")

    @ExcelProperty(value = "freightNum")
    private Integer freightNum;
//货梯载重量（kg）
     @ApiModelProperty(value = "货梯载重量（kg）")

    @ExcelProperty(value = "freightLoad")
    private Double freightLoad;
//户型空置面积
     @ApiModelProperty(value = "户型空置面积")

    @ExcelProperty(value = "vacantArea")
    private Double vacantArea;
//装修
     @ApiModelProperty(value = "装修")

    @ExcelProperty(value = "fitment")
    private String fitment;
//容积率%
     @ApiModelProperty(value = "容积率%")

    @ExcelProperty(value = "plotRatio")
    private Double plotRatio;
//停车位
     @ApiModelProperty(value = "停车位")

    @ExcelProperty(value = "parkingSpace")
    private Double parkingSpace;
//停车费用
     @ApiModelProperty(value = "停车费用")

    @ExcelProperty(value = "parkingFee")
    private Double parkingFee;
//联系人
     @ApiModelProperty(value = "联系人")

    @ExcelProperty(value = "concatPerson")
    private String concatPerson;
//联系方式
     @ApiModelProperty(value = "联系方式")

    @ExcelProperty(value = "concatPhone")
    private String concatPhone;
//当前售价
     @ApiModelProperty(value = "当前售价")

    @ExcelProperty(value = "sellingPrice")
    private Double sellingPrice;
//创建人
     @ApiModelProperty(value = "创建人")

    @ExcelProperty(value = "creator")
    private String creator;
//创建时间
     @ApiModelProperty(value = "创建时间")

    @ExcelProperty(value = "createTime")
    private Date createTime;
//更新人
     @ApiModelProperty(value = "更新人")

    @ExcelProperty(value = "updator")
    private String updator;
//更新时间
     @ApiModelProperty(value = "更新时间")

    @ExcelProperty(value = "updateTime")
    private Date updateTime;

}

