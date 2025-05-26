package com.yins.health.controller;



import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.TbViewDto;
import com.yins.health.service.TbViewService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.io.Serializable;

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
    @GetMapping("/selectAll")
    public AppResult selectAll(@RequestBody TbViewDto tbViewDto) {
        return AppResult.successResult(this.tbViewService.pageByTbAdd(tbViewDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
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
    public AppResult insert(@RequestBody TbView tbView) {
        return AppResult.successResult(this.tbViewService.save(tbView));
    }

    /**
     * 修改数据
     *
     * @param tbView 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public AppResult update(@RequestBody TbView tbView) {
        return AppResult.successResult(this.tbViewService.updateById(tbView));
    }
}

