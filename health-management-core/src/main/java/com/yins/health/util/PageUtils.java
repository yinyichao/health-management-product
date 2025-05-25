package com.yins.health.util;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yins.health.base.mvc.PageQuery;
import com.yins.health.util.http.HttpServletUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 按page方式的的输入输出
 * 输入：各种入参命名的 startpage
 * 输出：各种page List的转换
 * @author wvp
 * @date 2022/11/16
 */
public class PageUtils {
    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }


    /**
     * 开启分页
     * 默认走的是PageHelper那套
     * 如果不传PageQuery，则需是get表单提交
     */
    public static void startPage() {
        startPage(true);
    }

    public static void startPage(Boolean count) {
        PageQuery pageQuery = buildPageRequest();
        startPage(pageQuery, count);
    }

    public static void startPage(PageQuery pageQuery,  Boolean count) {
        if(pageQuery==null){
            pageQuery = new PageQuery();
        }
        String orderBySql =  PageUtils.escapeOrderBySql(pageQuery.getOrderBy());
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize(), count).setOrderBy(orderBySql);
    }

    /**
     * 从request get表单参数中，取分页信息
     */
    public static PageQuery buildPageRequest(){
        Integer pageNum = HttpServletUtils.getParameterToInt(PageQuery.pageHeperMap.get("pageNum"));
        Integer pageSize = HttpServletUtils.getParameterToInt(PageQuery.pageHeperMap.get("pageSize"));
        String orderByColumn = HttpServletUtils.getParameter(PageQuery.pageHeperMap.get("orderByColumn"));
        String ace = HttpServletUtils.getParameter(PageQuery.pageHeperMap.get("ace"));
        if(pageNum==null || pageSize==null){
            return new PageQuery();
        }
        return new PageQuery(pageNum, pageSize, orderByColumn, ace);
    }

    /**
     * 清除Threadlocal分页
     * pagehelper的那套
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }





    /**
     * pagehelper.page<T> 转 pageInfo<P>，且泛型也转
     * 其中T一般是DO，R是DTO或者VO
     * @param source
     * @param constructor
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> PageInfo<R> pageToPageInfo(Page<T> source, Function<T, R> constructor){
        PageInfo<R> res = new PageInfo<>();
        res.setList(source.stream().map(constructor).collect(Collectors.toList()));
        res.setPageSize(source.getPageSize());
        res.setPageNum(source.getPageNum());
        res.setTotal(source.getTotal());
        return res;
    }

    public static <T> PageInfo<T> pageToPageInfo(Page<T> source){
        return new PageInfo<>(source);
    }

    /**
     * 设置count，并构建返回分页信息参数
     *
     * @param count
     * @param pageInfo
     */
    public static void setCount(Integer count, PageInfo pageInfo) {
        pageInfo.setTotal(count);
        int pages = count == 0 || pageInfo.getPageSize() == 0 ? 0 : count / pageInfo.getPageSize();
        pageInfo.setPages(pages+1);
        pageInfo.calcByNavigatePages(pageInfo.getPageSize() == 0 ? 10:pageInfo.getPageSize());
    }
}
