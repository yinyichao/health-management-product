package com.yins.health.base.controller;

import com.github.pagehelper.PageInfo;
import com.yins.health.base.page.TableDataInfo;
import com.yins.health.base.service.FastPageInterface;
import com.yins.health.constant.ReturnCode;

import java.util.List;

/**
 * 基础controller
 * 仅加分页等功能，若需关联表操作见 BaseTableController
 * @author wvp
 * @date 2023/3/10 18:44
 */
public abstract class BaseController implements FastPageInterface {


    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ReturnCode.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list, int total)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ReturnCode.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list, Object object)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ReturnCode.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setObject(object);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list, Object object, long total)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ReturnCode.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setObject(object);
        rspData.setTotal(total);
        return rspData;
    }

    protected TableDataInfo getDataTable(List<?> list, Object object, Object objectLabel, long total)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ReturnCode.SUCCESS.getCode());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setObject(object);
        rspData.setObjectLabel(objectLabel);
        rspData.setTotal(total);
        return rspData;
    }
}
