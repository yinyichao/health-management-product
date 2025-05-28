package com.yins.health.controller;



import com.yins.health.entity.dto.TbTaskDto;
import com.yins.health.entity.vo.TbTaskVo;
import com.yins.health.service.TbTaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
import com.yins.health.util.AppResult;

/**
 * 任务表;(TbTask)表控制层
 *
 * @author yinyichao
 * @since 2025-05-28 15:14:03
 */
@Api(tags = "任务表;API")
@RestController
@RequestMapping("/api/task/v1")
public class TbTaskController {
    /**
     * 服务对象
     */
    @Resource
    private TbTaskService tbTaskService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbTaskDto tbTaskDto) {
        return AppResult.successResult(this.tbTaskService.selectByPage(tbTaskDto));
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
        return AppResult.successResult(this.tbTaskService.getTbTaskById(id));
    }

    /**
     * 新增数据
     *
     * @param tbTaskVo 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbTaskVo tbTaskVo) {
        this.tbTaskService.saveTbTask(tbTaskVo);
        return AppResult.successResult("");
    }

    /**
     * 修改数据
     *
     * @param tbTaskVo 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbTaskVo tbTaskVo) {
        this.tbTaskService.updateTbTask(tbTaskVo);
        return AppResult.successResult("");
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除结果")
    public AppResult delete(@RequestParam("id") Integer id) {
        this.tbTaskService.removeTbTask(id);
        return AppResult.successResult("");
    }
}

