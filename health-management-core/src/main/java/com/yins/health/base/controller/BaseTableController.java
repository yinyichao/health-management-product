package com.yins.health.base.controller;

import com.yins.health.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 绑定单个表操作的Controller
 * 具体是哪个表，见M, T
 * @param <M>
 * @param <T> 一般是数据库单表的PO
 *
 * @author wvp
 * @date 2023/3/10 18:44
 */
public abstract class BaseTableController<M extends BaseService, T> extends BaseController {

    @Autowired
    protected M baseService;


}
