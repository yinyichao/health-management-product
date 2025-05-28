package com.yins.health.controller;



import com.yins.health.entity.TbQuestionnaire;
import com.yins.health.entity.dto.TbQuestionnaireDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbQuestionnaireService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;

import com.yins.health.util.AppResult;

/**
 * 采集问卷管理表;(TbQuestionnaire)表控制层
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Api(tags = "采集问卷管理表;API")
@RestController
@RequestMapping("/api/questionnaire/v1")
public class TbQuestionnaireController {
    /**
     * 服务对象
     */
    @Resource
    private TbQuestionnaireService tbQuestionnaireService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/selectAll")
    @Operation(summary = "分页查询所有数据")
    public AppResult selectAll(@RequestBody TbQuestionnaireDto tbQuestionnaireDto) {
        return AppResult.successResult(this.tbQuestionnaireService.selectByPage(tbQuestionnaireDto));
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
        return AppResult.successResult(this.tbQuestionnaireService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tbQuestionnaire 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    @Operation(summary = "新增数据")
    public AppResult insert(@RequestBody TbQuestionnaire tbQuestionnaire) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaire.setCreatedUser(String.valueOf(userid));
        return AppResult.successResult(this.tbQuestionnaireService.save(tbQuestionnaire));
    }

    /**
     * 修改数据
     *
     * @param tbQuestionnaire 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    @Operation(summary = "修改数据")
    public AppResult update(@RequestBody TbQuestionnaire tbQuestionnaire) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaire.setUpdatedUser(String.valueOf(userid));
        return AppResult.successResult(this.tbQuestionnaireService.updateById(tbQuestionnaire));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除结果")
    public AppResult delete(@RequestParam("id") Integer id) {
        return AppResult.successResult(this.tbQuestionnaireService.delete(id));
    }
}

