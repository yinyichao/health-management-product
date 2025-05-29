package com.yins.health.controller;



import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.service.TbStatisticsItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;

import com.yins.health.util.AppResult;

/**
 * (TbStatisticsItem)表控制层
 *
 * @author yinyichao
 * @since 2025-05-29 14:53:00
 */
@Api(tags = "绩效统计;API")
@RestController
@RequestMapping("/api/statisticsItem/v1")
public class TbStatisticsItemController {
    /**
     * 服务对象
     */
    @Resource
    private TbStatisticsItemService tbStatisticsItemService;

    /**
     * 查询所有数据
     *
     * @return 查询所有数据
     */
    @PostMapping("/selectMonthAll")
    @Operation(summary = "查询所有数据【月度】")
    public AppResult selectMonthAll(@RequestBody TbStatisticsItemDto tbStatisticsItemDto) {
        return AppResult.successResult(tbStatisticsItemService.selectMonthAll(tbStatisticsItemDto));
    }
    /**
     * 查询所有数据
     *
     * @return 查询所有数据
     */
    @PostMapping("/selectYearAll")
    @Operation(summary = "查询所有数据【年】")
    public AppResult selectYearAll(@RequestBody TbStatisticsItemDto tbStatisticsItemDto) {
        return AppResult.successResult(tbStatisticsItemService.selectYearAll(tbStatisticsItemDto));
    }
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public AppResult selectOne() {
        tbStatisticsItemService.month();
        return AppResult.successResult("");
    }
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectTwo")
    public AppResult selectTwo() {
        tbStatisticsItemService.day_week();
        return AppResult.successResult("");
    }

}

