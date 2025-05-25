package com.yins.health.base.service;


import com.yins.health.base.mvc.PageQuery;
import com.yins.health.util.PageUtils;

/**
 * 快速分页的支持接口
 * @author wvp
 * @date 2023/3/10 18:34
 */
public interface FastPageInterface {
    /**
     *开启分页
     * @param pageQuery   get表单请求，可不传dto，其他都要传pagedto
     * @param count 是否自动count sql
     */
    default void startPage(PageQuery pageQuery, Boolean count) {
        PageUtils.startPage(pageQuery,count);
    }

    default void startPage() {
        PageUtils.startPage();
    }

    default void startPage(Boolean count) {
        PageUtils.startPage(count);
    }


    default void startPage(PageQuery pageQuery) {
        PageUtils.startPage(pageQuery, true);
    }

    default void clearPage() {
        PageUtils.clearPage();
    }

}
