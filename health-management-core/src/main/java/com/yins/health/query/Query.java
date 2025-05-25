package com.yins.health.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author XR
 * @Description 分页入参
 * @Date 2024/9/24
 */
@Getter
@Setter
@ApiModel(description = "查询条件")
public class Query {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageNum=1;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer pageSize=10;

}
