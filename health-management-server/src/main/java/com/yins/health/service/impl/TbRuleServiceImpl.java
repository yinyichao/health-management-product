package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbRuleDao;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.dto.TbRuleDto;
import com.yins.health.entity.vo.TbRuleVo;
import com.yins.health.interceptor.LoginInterceptor;
import com.yins.health.service.TbRuleService;
import org.springframework.stereotype.Service;

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
        Integer userid = LoginInterceptor.threadLocal.get().getId();
        tbRule.setUpdatedUser(String.valueOf(userid));
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

