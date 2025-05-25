package com.yins.health.base.mvc;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wvp
 * @date 2023/1/4 19:51
 */
@ApiModel(description = "分页")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页个数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序列")
    private String orderByColumn;

    @ApiModelProperty(value = "排序的方向'desc'或者'asc'")
    private String asc = "asc";

    @JsonIgnore
    @JSONField(serialize=false)
    public static Map<String, String> pageHeperMap = new HashMap(){{
        put("pageNum", "pageNum");
        put("pageSize", "pageSize");
        put("orderByColumn", "orderByColumn");
        put("asc", "asc");
    }};

    @JsonIgnore
    @JSONField(serialize=false)
    public static Map<String, String> MybatisPlusMap = new HashMap(){{
        put("pageNum", "current");
        put("pageSize", "size");
    }};

    @ApiModelProperty(hidden = true)
    public String getOrderBy() {
        if (StringUtils.isBlank(orderByColumn)) {
            return "";
        }
        return orderByColumn + " " + asc;
    }

    /**
     * 转mybatis ++ 的命名规则，IPage的那套
     * @return
     */
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page toMPlusPage(){
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageNum.longValue(), pageSize.longValue());
        if(StringUtils.isNotBlank(orderByColumn)){
            if(asc.equals("asc") || asc.equals("ASC")){
                page.addOrder(OrderItem.asc(orderByColumn));
            }
            if(asc.equals("desc") || asc.equals("DESC")){
                page.addOrder(OrderItem.desc(orderByColumn));
            }
        }
        return page;
    }

    /**
     * 转pagehelper的命名规则 (默认)
     * @return
     */
    public com.github.pagehelper.Page toMHelperPage(){
        return new com.github.pagehelper.Page(pageNum, pageSize);
    }


}
