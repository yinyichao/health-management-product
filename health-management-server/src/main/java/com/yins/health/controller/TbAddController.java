package com.yins.health.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yins.health.entity.TbAdd;
import com.yins.health.entity.dto.TbAddDto;
import com.yins.health.service.TbAddService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
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
    public AppResult selectAll(@RequestBody TbAddDto tbAddDto) {
        return AppResult.successResult(this.tbAddService.pageByTbAdd(tbAddDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
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
    public AppResult insert(@RequestBody TbAdd tbAdd) {
        return AppResult.successResult(this.tbAddService.save(tbAdd));
    }

    /**
     * 修改数据
     *
     * @param tbAdd 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public AppResult update(@RequestBody TbAdd tbAdd) {
        return AppResult.successResult(this.tbAddService.updateById(tbAdd));
    }

}

