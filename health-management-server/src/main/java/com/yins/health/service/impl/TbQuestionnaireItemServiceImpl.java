package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbQuestionnaireItemDao;
import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.vo.TbQuestionnaireItemVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbQuestionnaireItemService;
import com.yins.health.service.TbRuleModelService;
import com.yins.health.service.TbRuleService;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 问卷收集表;(TbQuestionnaireItem)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-28 14:06:23
 */
@Service("tbQuestionnaireItemService")
public class TbQuestionnaireItemServiceImpl extends ServiceImpl<TbQuestionnaireItemDao, TbQuestionnaireItem> implements TbQuestionnaireItemService {
    @Autowired
    private TbRuleService tbRuleService;
    @Autowired
    private TbRuleModelService tbRuleModelService;

    @Override
    public TbQuestionnaireItemVo getTbQuestionnaireItemVoById(Serializable id) {
        return baseMapper.getTbQuestionnaireItemVoById(id);
    }

    @Override
    public void saveTbQuestionnaireItem(TbQuestionnaireItem tbQuestionnaireItem) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaireItem.setCreatedUser(userid);
        List<TbRule> tbRuleList = tbRuleService.list(new LambdaQueryWrapper<TbRule>().eq(TbRule::getDel, 0)
                .eq(TbRule::getState, 0).eq(TbRule::getType, "采集"));
        String beginTime = "";
        String ruleType = "";
        List<TbRuleModel> list = new ArrayList<>();
        for (TbRule tbRule : tbRuleList) {
            switch (tbRule.getCycle()) {
                case "每月":
                    beginTime = TbRuleModelUtil.month();
                    break;
                case "每周":
                    beginTime = TbRuleModelUtil.week();
                    break;
                case "每日":
                    beginTime = TbRuleModelUtil.day();
                    break;
                case "小时":
                    beginTime = TbRuleModelUtil.hours(tbRule.getHours());
                    break;
            }
            Integer counts = baseMapper.selectCount(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                    .ge(TbQuestionnaireItem::getUpdatedTime, beginTime));
            ruleType = TbRuleModelUtil.getString(tbRule, counts, ruleType, list);
        }
        tbQuestionnaireItem.setLabel(ruleType);
        baseMapper.insert(tbQuestionnaireItem);
        for(TbRuleModel tbRuleModel : list){
            tbRuleModel.setModelId(tbQuestionnaireItem.getId());
        }
        tbRuleModelService.saveBatch(list);
    }

    @Override
    public IPage<TbQuestionnaireItemVo> pageByTbQuestionnaireItem(TbQuestionnaireItemDto tbQuestionnaireItemDto) {
        IPage<TbQuestionnaireItemVo> page = new Page<>();
        page.setCurrent(tbQuestionnaireItemDto.getPageNum());
        page.setSize(tbQuestionnaireItemDto.getPageSize());
        return baseMapper.selectByPage(page,tbQuestionnaireItemDto);
    }

    @Override
    public List<TbStatisticsItemVDto> findTbStatisticsItemVDto(String userId,String beginTime,String endTime) {
        return baseMapper.findTbStatisticsItemVDto(userId,beginTime,endTime);
    }
}

