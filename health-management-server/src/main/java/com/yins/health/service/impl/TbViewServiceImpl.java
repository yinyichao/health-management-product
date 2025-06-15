package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbViewDao;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.RuleDto;
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
import java.util.Collections;
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
        Integer isAdmin = LoginInterceptor.threadLocal.get().getIsAdmin();
        LambdaQueryWrapper<TbView> wrapper = new LambdaQueryWrapper<TbView>()
                .eq(TbView::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbViewDto.getLabel()), TbView::getLabel, tbViewDto.getLabel());
        // 只有 visitor 非空时才添加 OR 条件
        if (StringUtils.isNotEmpty(tbViewDto.getVisitor())) {
            wrapper.and(w -> w
                    .eq(TbView::getVisitor, tbViewDto.getVisitor())
                    .or()
                    .eq(TbView::getUsername, tbViewDto.getVisitor())
            );
        }
        if(isAdmin == 0){
            wrapper.eq(TbView::getCreatedUser,LoginInterceptor.threadLocal.get().getId());
        }
        wrapper.eq(StringUtils.isNotEmpty(tbViewDto.getVisitedPerson()), TbView::getVisitedPerson, tbViewDto.getVisitedPerson())
                .eq(StringUtils.isNotEmpty(tbViewDto.getState()), TbView::getState, tbViewDto.getState())
                .ge(StringUtils.isNotEmpty(tbViewDto.getBeginTime()), TbView::getCreatedTime, tbViewDto.getBeginDateTime())
                .le(StringUtils.isNotEmpty(tbViewDto.getEndTime()), TbView::getCreatedTime, tbViewDto.getEndDateTime())
                .orderByDesc(TbView::getCreatedTime);
        return baseMapper.selectPage(page,wrapper);
    }

    @Override
    public List<TbView> listByTbAdd(TbViewDto tbViewDto) {
        Integer isAdmin = LoginInterceptor.threadLocal.get().getIsAdmin();
        LambdaQueryWrapper<TbView> wrapper = new LambdaQueryWrapper<TbView>()
                .eq(TbView::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbViewDto.getLabel()), TbView::getLabel, tbViewDto.getLabel());
        // 只有 visitor 非空时才添加 OR 条件
        if (StringUtils.isNotEmpty(tbViewDto.getVisitor())) {
            wrapper.and(w -> w
                    .eq(TbView::getVisitor, tbViewDto.getVisitor())
                    .or()
                    .eq(TbView::getUsername, tbViewDto.getVisitor())
            );
        }
        if(isAdmin == 0){
            wrapper.eq(TbView::getCreatedUser,LoginInterceptor.threadLocal.get().getId());
        }
        wrapper.eq(StringUtils.isNotEmpty(tbViewDto.getVisitedPerson()), TbView::getVisitedPerson, tbViewDto.getVisitedPerson())
                .eq(StringUtils.isNotEmpty(tbViewDto.getState()), TbView::getState, tbViewDto.getState())
                .ge(StringUtils.isNotEmpty(tbViewDto.getBeginTime()), TbView::getCreatedTime, tbViewDto.getBeginDateTime())
                .le(StringUtils.isNotEmpty(tbViewDto.getEndTime()), TbView::getCreatedTime, tbViewDto.getEndDateTime())
                .orderByDesc(TbView::getCreatedTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void saveTbView(TbView tbView) {
        String userid = LoginInterceptor.threadLocal.get().getId();
        String username = LoginInterceptor.threadLocal.get().getUsername();
        tbView.setCreatedUser(userid);
        tbView.setUsername(username);
        List<TbRule> tbRuleList = tbRuleService.list(new LambdaQueryWrapper<TbRule>().eq(TbRule::getDel, 0)
                .eq(TbRule::getState, 0).eq(TbRule::getType, "面见"));
        String beginTime = "";
        List<TbRuleModel> list = new ArrayList<>();
        RuleDto ruleDto = new RuleDto();
        for (TbRule tbRule : tbRuleList) {
            switch (tbRule.getCycle()) {
                case "月":
                    beginTime = TbRuleModelUtil.month();
                    break;
                case "周":
                    beginTime = TbRuleModelUtil.week();
                    break;
                case "日":
                    beginTime = TbRuleModelUtil.day();
                    break;
                case "小时":
                    beginTime = TbRuleModelUtil.hours(tbRule.getHours());
                    break;
            }
            Integer counts = baseMapper.selectCount(new LambdaQueryWrapper<TbView>().eq(TbView::getDel, 0).eq(TbView::getState, "有效")
                    .ge(TbView::getUpdatedTime, beginTime));
            TbRuleModelUtil.getString(tbRule, counts, ruleDto, list);
        }
        tbView.setLabel(ruleDto.getLabel());
        tbView.setLabelContent(ruleDto.getContent());
        if(ruleDto.getLabel().equals("高风险")){
            tbView.setState("作废");
        }
        baseMapper.insert(tbView);
        for(TbRuleModel tbRuleModel : list){
            tbRuleModel.setModelId(tbView.getId());
        }
        tbRuleModelService.saveBatch(list);
    }

    @Override
    public List<TbStatisticsItemVDto> findTbStatisticsItemVDto(String userId,String beginTime,String endTime) {
        return baseMapper.findTbStatisticsItemVDto(userId,beginTime,endTime);
    }
}

