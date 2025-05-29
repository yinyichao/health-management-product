package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbViewDao;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.TbStatisticsItemVDto;
import com.yins.health.entity.dto.TbViewDto;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbRuleModelService;
import com.yins.health.service.TbRuleService;
import com.yins.health.service.TbViewService;
import com.yins.health.util.StringUtils;
import com.yins.health.util.TbRuleModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 面见管理表;(TbView)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
@Service("tbViewService")
public class TbViewServiceImpl extends ServiceImpl<TbViewDao, TbView> implements TbViewService {
    @Autowired
    private TbRuleService tbRuleService;
    @Autowired
    private TbRuleModelService tbRuleModelService;

    @Override
    public IPage<TbView> pageByTbAdd(TbViewDto tbViewDto) {
        IPage<TbView> page = new Page<>();
        page.setCurrent(tbViewDto.getPageNum());
        page.setSize(tbViewDto.getPageSize());
        return baseMapper.selectPage(page, new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbViewDto.getLabel()), TbView::getLabel, tbViewDto.getLabel())
                .eq(StringUtils.isNotEmpty(tbViewDto.getVisitor()), TbView::getVisitor, tbViewDto.getVisitor())
                .eq(StringUtils.isNotEmpty(tbViewDto.getVisitedPerson()), TbView::getVisitedPerson, tbViewDto.getVisitedPerson())
                .eq(StringUtils.isNotEmpty(tbViewDto.getState()), TbView::getState, tbViewDto.getState())
                .ge(StringUtils.isNotEmpty(tbViewDto.getBeginTime()), TbView::getCreatedTime, tbViewDto.getBeginTime())
                .le(StringUtils.isNotEmpty(tbViewDto.getEndTime()), TbView::getCreatedTime, tbViewDto.getEndTime()));
    }

    @Override
    public void saveTbView(TbView tbView) {
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbView.setCreatedUser(String.valueOf(userid));
        List<TbRule> tbRuleList = tbRuleService.list(new LambdaQueryWrapper<TbRule>().eq(TbRule::getDel, 0)
                .eq(TbRule::getState, 0).eq(TbRule::getType, "面见"));
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
            Integer counts = baseMapper.selectCount(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                    .ge(TbView::getUpdatedTime, beginTime));
            ruleType = TbRuleModelUtil.getString(tbRule, counts, ruleType, list);
        }
        tbView.setLabel(ruleType);
        baseMapper.insert(tbView);
        for(TbRuleModel tbRuleModel : list){
            tbRuleModel.setModelId(tbView.getId());
        }
        tbRuleModelService.saveBatch(list);
    }

    @Override
    public List<TbStatisticsItemVDto> findTbStatisticsItemVDto(Integer userId,String beginTime,String endTime) {
        return baseMapper.findTbStatisticsItemVDto(userId,beginTime,endTime);
    }
}

