package com.yins.health.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yins.health.entity.TbTalentPolicy;
import com.yins.health.service.TbTalentPolicyService;
import com.yins.health.util.AppResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 人才政策主表(TbTalentPolicy)表控制层
 *
 * @author yinyichao
 * @since 2025-05-13 11:17:54
 */
@Api(tags = "人才政策主表API")
@RestController
@RequestMapping("/api/tbTalentPolicy/v1")
public class TbTalentPolicyController {
    /**
     * 服务对象
     */
    @Resource
    private TbTalentPolicyService tbTalentPolicyService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param tbTalentPolicy 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public AppResult selectAll(Page<TbTalentPolicy> page, TbTalentPolicy tbTalentPolicy) {
        return AppResult.successResult(this.tbTalentPolicyService.page(page, new QueryWrapper<>(tbTalentPolicy)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public AppResult selectOne(@PathVariable Serializable id) {
        return AppResult.successResult(this.tbTalentPolicyService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbTalentPolicy 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public AppResult insert(@RequestBody TbTalentPolicy tbTalentPolicy) {
        return AppResult.successResult(this.tbTalentPolicyService.save(tbTalentPolicy));
    }

    /**
     * 修改数据
     *
     * @param tbTalentPolicy 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public AppResult update(@RequestBody TbTalentPolicy tbTalentPolicy) {
        return AppResult.successResult(this.tbTalentPolicyService.updateById(tbTalentPolicy));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/delete")
    public AppResult delete(@RequestParam("idList") List<Long> idList) {
        return AppResult.successResult(this.tbTalentPolicyService.removeByIds(idList));
    }
}

