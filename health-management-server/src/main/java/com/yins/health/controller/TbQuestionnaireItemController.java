package com.yins.health.controller;



import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.entity.vo.TbQuestionnaireItemVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbQuestionnaireItemService;
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
 * 问卷收集表;(TbQuestionnaireItem)表控制层
 *
 * @author yinyichao
 * @since 2025-05-28 14:06:23
 */
@Api(tags = "问卷收集表;API")
@RestController
@RequestMapping("/api/questionnaireItem/v1")
public class TbQuestionnaireItemController {
    /**
     * 服务对象
     */
    @Resource
    private TbQuestionnaireItemService tbQuestionnaireItemService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbQuestionnaireItemDto tbQuestionnaireItemDto) {
        return AppResult.successResult(this.tbQuestionnaireItemService.pageByTbQuestionnaireItem(tbQuestionnaireItemDto));
    }
    @ApiOperation(value = "问卷收集结果导出", notes = "问卷收集结果导出")
    @PostMapping(value = "/exportView")
    public void exportView(@RequestBody TbQuestionnaireItemDto tbQuestionnaireItemDto, HttpServletResponse response) throws Exception {
        List<TbQuestionnaireItemVo> list = tbQuestionnaireItemService.listByTbAdd(tbQuestionnaireItemDto);
        ExcelUtil.exportTemplateDate(response, "问卷收集结果", TbQuestionnaireItemVo.class, list, "问卷收集结果");
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
        return AppResult.successResult(this.tbQuestionnaireItemService.getTbQuestionnaireItemVoById(id));
    }

    /**
     * 新增数据
     *
     * @param tbQuestionnaireItem 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbQuestionnaireItem tbQuestionnaireItem) {
        this.tbQuestionnaireItemService.saveTbQuestionnaireItem(tbQuestionnaireItem);
        return AppResult.emptyResult();
    }

    /**
     * 修改数据
     *
     * @param tbQuestionnaireItem 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbQuestionnaireItem tbQuestionnaireItem) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaireItem.setUpdatedUser(userid);
        return AppResult.successResult(this.tbQuestionnaireItemService.updateById(tbQuestionnaireItem));
    }

}

