package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbQuestionnaireItemDao;
import com.yins.health.entity.TbQuestionnaireItem;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.dto.RuleDto;
import com.yins.health.entity.dto.TbQuestionnaireItemDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.vo.TbQuestionnaireItemVo;
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
        /*String userid = LoginInterceptor.threadLocal.get().getId();
        tbQuestionnaireItem.setCreatedUser(userid);*/
        List<TbRule> tbRuleList = tbRuleService.list(new LambdaQueryWrapper<TbRule>().eq(TbRule::getDel, 0)
                .eq(TbRule::getState, 0).eq(TbRule::getType, "采集"));
        String beginTime = "";
        RuleDto ruleDto = new RuleDto();
        List<TbRuleModel> list = new ArrayList<>();
        for (TbRule tbRule : tbRuleList) {
            Integer counts = 0;
            if(tbRule.getRule().equals("特殊规则")){
                switch (tbRule.getCycle()) {
                    case "月":
                        beginTime = TbRuleModelUtil.month(tbRule.getHours());
                        break;
                    case "周":
                        beginTime = TbRuleModelUtil.week(tbRule.getHours());
                        break;
                    case "日":
                        beginTime = TbRuleModelUtil.day(tbRule.getHours());
                        break;
                    case "小时":
                        beginTime = TbRuleModelUtil.hours(tbRule.getHours());
                        break;
                }
                counts = baseMapper.selectCount(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                        .eq(TbQuestionnaireItem::getName,tbQuestionnaireItem.getName()).ge(TbQuestionnaireItem::getUpdatedTime, beginTime));
            }else{
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
                counts = baseMapper.selectCount(new LambdaQueryWrapper<TbQuestionnaireItem>().eq(TbQuestionnaireItem::getDel, 0).eq(TbQuestionnaireItem::getState, "有效")
                        .ge(TbQuestionnaireItem::getUpdatedTime, beginTime));
            }
            TbRuleModelUtil.getString(tbRule, counts, ruleDto, list);
        }
        tbQuestionnaireItem.setLabel(ruleDto.getLabel());
        tbQuestionnaireItem.setLabelContent(ruleDto.getContent());
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
        tbQuestionnaireItemDto.setBeginTime(tbQuestionnaireItemDto.getBeginDateTime());
        tbQuestionnaireItemDto.setEndTime(tbQuestionnaireItemDto.getEndDateTime());
        return baseMapper.selectByPage(page,tbQuestionnaireItemDto);
    }

    @Override
    public List<TbStatisticsItemVDto> findTbStatisticsItemVDto(String userId,String beginTime,String endTime) {
        return baseMapper.findTbStatisticsItemVDto(userId,beginTime,endTime);
    }

    @Override
    public List<TbQuestionnaireItemVo> listByTbAdd(TbQuestionnaireItemDto tbQuestionnaireItemDto) {
        tbQuestionnaireItemDto.setBeginTime(tbQuestionnaireItemDto.getBeginDateTime());
        tbQuestionnaireItemDto.setEndTime(tbQuestionnaireItemDto.getEndDateTime());
        return baseMapper.selectByList(tbQuestionnaireItemDto);
    }
}

