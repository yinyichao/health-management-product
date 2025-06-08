package com.yins.health.controller;



import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.TbViewDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbViewService;
import com.yins.health.util.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

import com.yins.health.util.AppResult;

/**
 * 面见管理表;(TbView)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
@Api(tags = "面见管理表;API")
@RestController
@RequestMapping("/api/view/v1")
public class TbViewController {
    /**
     * 服务对象
     */
    @Resource
    private TbViewService tbViewService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbViewDto tbViewDto) {
        return AppResult.successResult(this.tbViewService.pageByTbAdd(tbViewDto));
    }
    @ApiOperation(value = "面见结果导出", notes = "面见结果导出")
    @PostMapping(value = "/exportView")
    public void exportView(@RequestBody TbViewDto tbViewDto, HttpServletResponse response) throws Exception {
        List<TbView> list = tbViewService.listByTbAdd(tbViewDto);
        ExcelUtil.exportTemplateDate(response, "面见结果", TbView.class, list, "面见结果");
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    @Operation(summary = "通过主键查询单条数据")
    public AppResult selectOne(@PathVariable Serializable id) {
        return AppResult.successResult(this.tbViewService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbView 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbView tbView) {
        this.tbViewService.saveTbView(tbView);
        return AppResult.successResult("");
    }

    /**
     * 修改数据
     *
     * @param tbView 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbView tbView) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbView.setUpdatedUser(userid);
        return AppResult.successResult(this.tbViewService.updateById(tbView));
    }
}

