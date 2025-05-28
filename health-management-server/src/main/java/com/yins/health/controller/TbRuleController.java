package com.yins.health.controller;



import com.yins.health.entity.TbRule;
import com.yins.health.entity.dto.TbRuleDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbRuleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;

import com.yins.health.util.AppResult;

/**
 * 风险规则表;(TbRule)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:35
 */
@Api(tags = "风险规则表;API")
@RestController
@RequestMapping("/api/rule/v1")
public class TbRuleController {
    /**
     * 服务对象
     */
    @Resource
    private TbRuleService tbRuleService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbRuleDto tbRuleDto) {
        return AppResult.successResult(this.tbRuleService.selectByPage(tbRuleDto));
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
        return AppResult.successResult(this.tbRuleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbRule 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbRule tbRule) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbRule.setCreatedUser(String.valueOf(userid));
        return AppResult.successResult(this.tbRuleService.save(tbRule));
    }

    /**
     * 修改数据
     *
     * @param tbRule 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbRule tbRule) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbRule.setUpdatedUser(String.valueOf(userid));
        return AppResult.successResult(this.tbRuleService.updateById(tbRule));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除结果")
    public AppResult delete(@RequestParam("id") Integer id) {
        return AppResult.successResult(this.tbRuleService.delete(id));
    }
}

