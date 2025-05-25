package com.yins.health.base.mvc;

import com.github.pagehelper.PageInfo;
import com.yins.health.util.Convert;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 带分页信息的返回bean
 * 例：R<PageListVo<T>>
 * pagehelper.page， pagehelper.pageinfo，mybatis-plus.ipage的转换方法
 * @author: wvp
 **/
@Data
public class PageListVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<T> list;


    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;


    public PageListVO<T> setPageListVo(PageInfo<?> page, List<T> list) {
        this.list = list;
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        return this;
    }

    /**
     * 传list，page，pageinfo都兼容
     * @param list
     * @param <R>
     * @return
     */
    public static <R> PageListVO<R> toPageListVo(List<R> list) {
        return PageListVO.toPageListVo(new PageInfo<>(list));
    }

    public static <R> PageListVO<R> toPageListVo(PageInfo<R> page) {
        PageListVO<R> res = new PageListVO<>();
        res.setPageListVo(page, page.getList());
        return res;
    }

    /**
     * 带类型转换的分页
     * @param list 兼容page
     * @param targetClazz
     * @param <S>
     * @param <R>
     * @return
     */
    public static <S, R> PageListVO<R> toPageListVo(List<S> list, Class<R> targetClazz) {
        return PageListVO.toPageListVo(Convert.toPage(list, targetClazz));
    }
}