package com.yins.health.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbRule;
import com.yins.health.entity.dto.TbRuleDto;
import com.yins.health.entity.vo.TbRuleVo;

/**
 * 风险规则表;(TbRule)表服务接口
 *
 * @author yinyichao
 * @since 2025-05-26 14:23:35
 */
public interface TbRuleService extends IService<TbRule> {

    Integer delete(Integer id);
    IPage<TbRuleVo> selectByPage(TbRuleDto tbRuleDto);
}

