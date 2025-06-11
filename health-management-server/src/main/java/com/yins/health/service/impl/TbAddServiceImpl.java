package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbAddDao;
import com.yins.health.entity.TbAdd;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.dto.RuleDto;
import com.yins.health.entity.dto.TbAddDto;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbAddService;
import com.yins.health.service.TbRuleModelService;
import com.yins.health.service.TbRuleService;
import com.yins.health.util.StringUtils;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 增员管理表;(TbAdd)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Service("tbAddService")
public class TbAddServiceImpl extends ServiceImpl<TbAddDao, TbAdd> implements TbAddService {

    @Autowired
    private TbRuleService tbRuleService;
    @Autowired
    private TbRuleModelService tbRuleModelService;


    @Override
    public IPage<TbAdd> pageByTbAdd(TbAddDto tbAddDto) {
        IPage<TbAdd> page = new Page<>();
        page.setCurrent(tbAddDto.getPageNum());
        page.setSize(tbAddDto.getPageSize());
        return baseMapper.selectPage(page, new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbAddDto.getLabel()), TbAdd::getLabel, tbAddDto.getLabel())
                .eq(StringUtils.isNotEmpty(tbAddDto.getName()), TbAdd::getName, tbAddDto.getName())
                .eq(StringUtils.isNotEmpty(tbAddDto.getUsername()), TbAdd::getUsername, tbAddDto.getUsername())
                .ge(StringUtils.isNotEmpty(tbAddDto.getBeginTime()), TbAdd::getCreatedTime, tbAddDto.getBeginDateTime())
                .le(StringUtils.isNotEmpty(tbAddDto.getEndTime()), TbAdd::getCreatedTime, tbAddDto.getEndDateTime()));
    }

    @Override
    public void saveTbAdd(TbAdd tbAdd) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        String username = LoginInterceptor.threadLocal.get().getUsername();
        tbAdd.setCreatedUser(userid);
        tbAdd.setUsername(username);
        List<TbRule> tbRuleList = tbRuleService.list(new LambdaQueryWrapper<TbRule>().eq(TbRule::getDel, 0)
                .eq(TbRule::getState, 0).eq(TbRule::getType, "增员"));
        String beginTime = "";
        RuleDto ruleDto = new RuleDto();
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
            Integer counts = baseMapper.selectCount(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0).eq(TbAdd::getState, "有效")
                    .ge(TbAdd::getUpdatedTime, beginTime));
            TbRuleModelUtil.getString(tbRule, counts, ruleDto, list);
        }
        tbAdd.setLabel(ruleDto.getLabel());
        tbAdd.setLabelContent(ruleDto.getContent());
        baseMapper.insert(tbAdd);
        for(TbRuleModel tbRuleModel : list){
            tbRuleModel.setModelId(tbAdd.getId());
        }
        tbRuleModelService.saveBatch(list);
    }

    @Override
    public List<TbStatisticsItemVDto> findTbStatisticsItemVDto(String userId,String beginTime,String endTime) {
        return baseMapper.findTbStatisticsItemVDto(userId,beginTime,endTime);
    }

    @Override
    public List<TbAdd> listByTbAdd(TbAddDto tbAddDto) {
        return baseMapper.selectList(new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbAddDto.getLabel()), TbAdd::getLabel, tbAddDto.getLabel())
                .eq(StringUtils.isNotEmpty(tbAddDto.getName()), TbAdd::getName, tbAddDto.getName())
                .eq(StringUtils.isNotEmpty(tbAddDto.getUsername()), TbAdd::getUsername, tbAddDto.getUsername())
                .ge(StringUtils.isNotEmpty(tbAddDto.getBeginTime()), TbAdd::getCreatedTime, tbAddDto.getBeginDateTime())
                .le(StringUtils.isNotEmpty(tbAddDto.getEndTime()), TbAdd::getCreatedTime, tbAddDto.getEndDateTime()));
    }


}

