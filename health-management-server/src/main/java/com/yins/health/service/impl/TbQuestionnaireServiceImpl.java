package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbQuestionnaireDao;
import com.yins.health.entity.TbQuestionnaire;
import com.yins.health.entity.dto.TbQuestionnaireDto;
import com.yins.health.entity.vo.TbQuestionnaireVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbQuestionnaireService;
import org.springframework.stereotype.Service;

/**
 * 采集问卷管理表;(TbQuestionnaire)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Service("tbQuestionnaireService")
public class TbQuestionnaireServiceImpl extends ServiceImpl<TbQuestionnaireDao, TbQuestionnaire> implements TbQuestionnaireService {

    @Override
    public Integer delete(Integer id) {
        TbQuestionnaire tbQuestionnaire = baseMapper.selectById(id);
        tbQuestionnaire.setDel(1);
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaire.setUpdatedUser(String.valueOf(userid));
        return baseMapper.updateById(tbQuestionnaire);
    }

    @Override
    public IPage<TbQuestionnaireVo> selectByPage(TbQuestionnaireDto tbQuestionnaireDto) {
        IPage<TbQuestionnaireVo> page = new Page<>();
        page.setCurrent(tbQuestionnaireDto.getPageNum());
        page.setSize(tbQuestionnaireDto.getPageSize());
        return baseMapper.selectByPage(page,tbQuestionnaireDto);
    }
}

