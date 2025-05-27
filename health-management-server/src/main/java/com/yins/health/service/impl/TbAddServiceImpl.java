package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbAddDao;
import com.yins.health.entity.TbAdd;
import com.yins.health.entity.dto.TbAddDto;
import com.yins.health.service.TbAddService;
import com.yins.health.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 增员管理表;(TbAdd)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 17:17:43
 */
@Service("tbAddService")
public class TbAddServiceImpl extends ServiceImpl<TbAddDao, TbAdd> implements TbAddService {

    @Override
    public IPage<TbAdd> pageByTbAdd(TbAddDto tbAddDto) {
        IPage<TbAdd> page = new Page<>();
        page.setCurrent(tbAddDto.getPageNum());
        page.setSize(tbAddDto.getPageSize());
        return baseMapper.selectPage(page, new LambdaQueryWrapper<TbAdd>().eq(TbAdd::getDel, 0)
                .eq(StringUtils.isNotEmpty(tbAddDto.getLabel()), TbAdd::getLabel, tbAddDto.getLabel())
                .eq(StringUtils.isNotEmpty(tbAddDto.getName()), TbAdd::getName, tbAddDto.getName())
                .eq(StringUtils.isNotEmpty(tbAddDto.getUsername()), TbAdd::getUsername, tbAddDto.getUsername())
                .ge(StringUtils.isNotEmpty(tbAddDto.getBeginTime()), TbAdd::getCreatedTime, tbAddDto.getBeginTime())
                .le(StringUtils.isNotEmpty(tbAddDto.getEndTime()), TbAdd::getCreatedTime, tbAddDto.getEndTime()));
    }
}

