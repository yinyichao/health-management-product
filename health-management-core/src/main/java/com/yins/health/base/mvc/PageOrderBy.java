package com.yins.health.base.mvc;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wvp
 * @date 2023/1/4 19:51
 */
@ApiModel(description = "分页,多字段排序")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageOrderBy {

    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页个数")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序字段true：asc、false: desc,{column1:true/false,column2:true/false...}")
    Map<String, Boolean> orderBy = new HashMap<>();

    @JsonIgnore
    @JSONField(serialize = false)
    public static Map<String, String> pageHeperMap = new HashMap() {{
        put("pageNum", "pageNum");
        put("pageSize", "pageSize");
        put("orderBy", "orderBy");
    }};

    @JsonIgnore
    @JSONField(serialize = false)
    public static Map<String, String> MybatisPlusMap = new HashMap() {{
        put("pageNum", "current");
        put("pageSize", "size");
    }};

    @ApiModelProperty(hidden = true)
    public Map<String, Boolean> getOrderBy() {
        if (CollectionUtils.isEmpty(orderBy)) {
            return null;
        }
        return orderBy;
    }

    /**
     * 转mybatis ++ 的命名规则，IPage的那套
     *
     * @return
     */
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page toMPlusPage() {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageNum.longValue(), pageSize.longValue());
        if (CollectionUtils.isNotEmpty(orderBy)) {
            Set<String> columns = orderBy.keySet();
            for (String column : columns) {
                page.addOrder(orderBy.get(column) ? OrderItem.asc(column) : OrderItem.desc(column));
            }
        }
        return page;
    }

    /**
     * 转pagehelper的命名规则 (默认)
     *
     * @return
     */
    public com.github.pagehelper.Page toMHelperPage() {
        return new com.github.pagehelper.Page(pageNum, pageSize);
    }


}
