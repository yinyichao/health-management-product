package com.yins.health.controller;



import com.yins.health.entity.dto.TbStatisticsItemDto;
import com.yins.health.entity.vo.TbStatisticsItemMonthVo;
import com.yins.health.entity.vo.TbStatisticsItemYearVo;
import com.yins.health.service.TbStatisticsItemService;
import com.yins.health.util.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.yins.health.util.AppResult;

import java.util.List;

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
    @Operation(summary = "绩效情况统计")
    public AppResult selectMonthAll(@RequestBody TbStatisticsItemDto tbStatisticsItemDto) {
        return AppResult.successResult(tbStatisticsItemService.selectMonthAll(tbStatisticsItemDto));
    }
    /**
     * 查询所有数据
     *
     * @return 查询所有数据
     */
    @PostMapping("/selectYearAll")
    @Operation(summary = "日常完成任务统计【年】")
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
        return AppResult.emptyResult();
    }
    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/selectTwo")
    public AppResult selectTwo() {
        tbStatisticsItemService.day_week();
        return AppResult.emptyResult();
    }
    @ApiOperation(value = "日常完成任务结果导出", notes = "日常完成任务结果导出")
    @PostMapping(value = "/exportYearAll")
    public void exportYearAll(@RequestBody TbStatisticsItemDto tbStatisticsItemDto, HttpServletResponse response) throws Exception {
        List<TbStatisticsItemYearVo> list = tbStatisticsItemService.selectYearAll(tbStatisticsItemDto);
        ExcelUtil.exportTemplateDate(response, "日常完成任务结果", TbStatisticsItemYearVo.class, list, "日常完成任务结果");
    }

    @ApiOperation(value = "绩效情况结果导出", notes = "绩效情况结果导出")
    @PostMapping(value = "/exportMonthAll")
    public void exportMonthAll(@RequestBody TbStatisticsItemDto tbStatisticsItemDto, HttpServletResponse response) throws Exception {
        List<TbStatisticsItemMonthVo> list = tbStatisticsItemService.selectMonthAll(tbStatisticsItemDto);
        ExcelUtil.exportTemplateDate(response, "绩效情况结果", TbStatisticsItemMonthVo.class, list, "绩效情况结果");
    }
}

