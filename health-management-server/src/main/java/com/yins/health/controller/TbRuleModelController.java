package com.yins.health.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.service.TbRuleModelService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.yins.health.util.AppResult;

/**
 * 风险规则命中表(TbRuleModel)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 17:09:03
 */
@Api(tags = "风险规则命中表API")
@RestController
@RequestMapping("tbRuleModel")
public class TbRuleModelController {
    /**
     * 服务对象
     */
    @Resource
    private TbRuleModelService tbRuleModelService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param tbRuleModel 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public AppResult selectAll(Page<TbRuleModel> page, TbRuleModel tbRuleModel) {
        return AppResult.successResult(this.tbRuleModelService.page(page, new QueryWrapper<>(tbRuleModel)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public AppResult selectOne(@PathVariable Serializable id) {
        return AppResult.successResult(this.tbRuleModelService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbRuleModel 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public AppResult insert(@RequestBody TbRuleModel tbRuleModel) {
        return AppResult.successResult(this.tbRuleModelService.save(tbRuleModel));
    }

    /**
     * 修改数据
     *
     * @param tbRuleModel 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public AppResult update(@RequestBody TbRuleModel tbRuleModel) {
        return AppResult.successResult(this.tbRuleModelService.updateById(tbRuleModel));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/delete")
    public AppResult delete(@RequestParam("idList") List<Long> idList) {
        return AppResult.successResult(this.tbRuleModelService.removeByIds(idList));
    }
}

