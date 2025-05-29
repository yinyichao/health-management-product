package com.yins.health.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.excel.annotation.ExcelProperty;

@Data
@NoArgsConstructor
@ApiModel("文件表;实体类")
@SuppressWarnings("serial")
@TableName("tb_file")
/**
 * 文件表;(TbFile)表实体类
 *
 * @author yinyichao
 * @since 2025-05-27 19:10:53
 */
public class TbFile extends Model<TbFile> {
//ID
     @ApiModelProperty(value = "ID")
     @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "id")
    private Integer id;
//文件名称
     @ApiModelProperty(value = "文件名称")

    @ExcelProperty(value = "fileName")
    private String fileName;
//文件的key, 格式 日期/md5.拓展名，比如 2024-11-13/921674fd-cdaf-459a-be7b-109469e7050d.png
     @ApiModelProperty(value = "文件的key, 格式 日期/md5.拓展名，比如 2024-11-13/921674fd-cdaf-459a-be7b-109469e7050d.png")

    @ExcelProperty(value = "objectKey")
    private String objectKey;
//逻辑删除（0未删除，1已删除）
     @ApiModelProperty(value = "逻辑删除（0未删除，1已删除）")

    @ExcelProperty(value = "del")
    private Integer del;
//创建时间
     @ApiModelProperty(value = "创建时间")
     @TableField(fill = FieldFill.INSERT)
    @ExcelProperty(value = "createdTime")
    private Date createdTime;
//更新时间
     @ApiModelProperty(value = "更新时间")
     @TableField(fill = FieldFill.INSERT_UPDATE)
    @ExcelProperty(value = "updatedTime")
    private Date updatedTime;
//创建用户
     @ApiModelProperty(value = "创建用户")

    @ExcelProperty(value = "createdUser")
    private String createdUser;
//更新用户
     @ApiModelProperty(value = "更新用户")

    @ExcelProperty(value = "updatedUser")
    private String updatedUser;

}

