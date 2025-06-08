package com.yins.health.controller;


import com.yins.health.entity.TbAdd;
import com.yins.health.entity.dto.TbAddDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbAddService;
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
 * 增员管理表;(TbAdd)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Api(tags = "增员管理表;API")
@RestController
@RequestMapping("/api/add/v1")
public class TbAddController {
    /**
     * 服务对象
     */
    @Resource
    private TbAddService tbAddService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbAddDto tbAddDto) {
        return AppResult.successResult(this.tbAddService.pageByTbAdd(tbAddDto));
    }
    @ApiOperation(value = "增员结果导出", notes = "增员结果导出")
    @PostMapping(value = "/exportView")
    public void exportView(@RequestBody TbAddDto tbAddDto, HttpServletResponse response) throws Exception {
        List<TbAdd> list = tbAddService.listByTbAdd(tbAddDto);
        ExcelUtil.exportTemplateDate(response, "增员结果", TbAdd.class, list, "增员结果");
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
        return AppResult.successResult(this.tbAddService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbAdd 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbAdd tbAdd) {
        this.tbAddService.saveTbAdd(tbAdd);
        return AppResult.successResult("");
    }

    /**
     * 修改数据
     *
     * @param tbAdd 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbAdd tbAdd) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbAdd.setUpdatedUser(userid);
        return AppResult.successResult(this.tbAddService.updateById(tbAdd));
    }

}

