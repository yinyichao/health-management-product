package com.yins.health.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yins.health.entity.TbSpaceHseF;
import com.yins.health.service.TbSpaceHseFService;
import com.yins.health.util.AppResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 空间用房(TbSpaceHseF)表控制层
 *
 * @author yinyichao
 * @since 2025-05-13 10:51:26
 */
@Api(tags = "空间用房API")
@RestController
@RequestMapping("/api/tbSpaceHseF/v1")
public class TbSpaceHseFController {
    /**
     * 服务对象
     */
    @Resource
    private TbSpaceHseFService tbSpaceHseFService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param tbSpaceHseF 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public AppResult selectAll(Page<TbSpaceHseF> page, TbSpaceHseF tbSpaceHseF) {
        return AppResult.successResult(this.tbSpaceHseFService.page(page, new QueryWrapper<>(tbSpaceHseF)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public AppResult selectOne(@PathVariable Serializable id) {
        return AppResult.successResult(this.tbSpaceHseFService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbSpaceHseF 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public AppResult insert(@RequestBody TbSpaceHseF tbSpaceHseF) {
        return AppResult.successResult(this.tbSpaceHseFService.save(tbSpaceHseF));
    }

    /**
     * 修改数据
     *
     * @param tbSpaceHseF 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    public AppResult update(@RequestBody TbSpaceHseF tbSpaceHseF) {
        return AppResult.successResult(this.tbSpaceHseFService.updateById(tbSpaceHseF));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @PostMapping("/delete")
    public AppResult delete(@RequestParam("idList") List<Long> idList) {
        return AppResult.successResult(this.tbSpaceHseFService.removeByIds(idList));
    }
}

