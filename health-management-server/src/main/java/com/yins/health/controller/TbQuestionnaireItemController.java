package com.yins.health.controller;



import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbQuestionnaireItemService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import java.io.Serializable;
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
    public AppResult selectAll(@RequestBody TbQuestionnaireItemDto tbQuestionnaireItemDto) {
        return AppResult.successResult(this.tbQuestionnaireItemService.pageByTbQuestionnaireItem(tbQuestionnaireItemDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
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
    public AppResult insert(@RequestBody TbQuestionnaireItem tbQuestionnaireItem) {
        this.tbQuestionnaireItemService.saveTbQuestionnaireItem(tbQuestionnaireItem);
        return AppResult.successResult("");
    }

    /**
     * 修改数据
     *
     * @param tbQuestionnaireItem 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public AppResult update(@RequestBody TbQuestionnaireItem tbQuestionnaireItem) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaireItem.setUpdatedUser(String.valueOf(userid));
        return AppResult.successResult(this.tbQuestionnaireItemService.updateById(tbQuestionnaireItem));
    }

}

