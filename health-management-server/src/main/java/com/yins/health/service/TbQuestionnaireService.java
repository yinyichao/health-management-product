package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbQuestionnaire;
import com.yins.health.entity.dto.TbQuestionnaireDto;
import com.yins.health.entity.vo.TbQuestionnaireVo;
import com.yins.health.entity.vo.TbRuleVo;

/**
 * 采集问卷管理表;(TbQuestionnaire)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
public interface TbQuestionnaireService extends IService<TbQuestionnaire> {

    Integer delete(Integer id);

    IPage<TbQuestionnaireVo> selectByPage(TbQuestionnaireDto tbQuestionnaireDto);
}

