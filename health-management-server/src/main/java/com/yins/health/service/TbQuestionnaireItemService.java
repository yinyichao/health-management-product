package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.entity.vo.TbQuestionnaireItemVo;

import java.io.Serializable;

/**
 * 问卷收集表;(TbQuestionnaireItem)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-28 14:06:23
 */
public interface TbQuestionnaireItemService extends IService<TbQuestionnaireItem> {

    TbQuestionnaireItemVo getTbQuestionnaireItemVoById(Serializable id);

    void saveTbQuestionnaireItem(TbQuestionnaireItem tbQuestionnaireItem);

    IPage<TbQuestionnaireItemVo> pageByTbQuestionnaireItem(TbQuestionnaireItemDto tbQuestionnaireItemDto);
}

