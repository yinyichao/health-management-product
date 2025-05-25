package com.yins.health.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModel {

    @TableField(exist = false)
    @ApiModelProperty(value = "当前页面", notes = "默认1", example = "1")
    private long current = 1;

    @TableField(exist = false)
    @ApiModelProperty(value = "每页显示条数", notes = "默认10", example = "10")
    private long size = 10;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序字段", notes = "对于model字段",required = false)
    private String sort;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序类型", notes = "asc 或者 desc", allowableValues = "asc,desc",required = false)
    private String order;

    @TableField(exist = false)
    @ApiModelProperty(required = false,hidden = true)
    private long offset;

}
