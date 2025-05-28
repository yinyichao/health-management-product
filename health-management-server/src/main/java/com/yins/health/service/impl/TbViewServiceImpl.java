package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbViewDao;
import com.yins.health.entity.TbView;
import com.yins.health.entity.dto.TbViewDto;
import com.yins.health.service.TbViewService;
import com.yins.health.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 面见管理表;(TbView)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:53
 */
@Service("tbViewService")
public class TbViewServiceImpl extends ServiceImpl<TbViewDao, TbView> implements TbViewService {

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
}

