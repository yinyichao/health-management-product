package com.yins.health.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbRuleDao;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.dto.TbRuleDto;
import com.yins.health.entity.vo.TbRuleVo;
import com.yins.health.service.TbRuleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 风险规则表;(TbRule)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:35
 */
@Service("tbRuleService")
public class TbRuleServiceImpl extends ServiceImpl<TbRuleDao, TbRule> implements TbRuleService {

    @Override
    public Integer delete(Integer id) {
        TbRule tbRule = baseMapper.selectById(id);
        tbRule.setDel(1);
        return baseMapper.updateById(tbRule);
    }

    @Override
    public IPage<TbRuleVo> selectByPage(TbRuleDto tbRuleDto) {
        IPage<TbRuleVo> page = new Page<>();
        page.setCurrent(tbRuleDto.getPageNum());
        page.setSize(tbRuleDto.getPageSize());
        return baseMapper.selectByPage(page,tbRuleDto);
    }
}

