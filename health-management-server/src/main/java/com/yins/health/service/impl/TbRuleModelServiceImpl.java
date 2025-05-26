package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbRuleModelDao;
import com.yins.health.entity.TbRuleModel;
import com.yins.health.service.TbRuleModelService;
import org.springframework.stereotype.Service;

/**
 * 风险规则命中表(TbRuleModel)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-26 17:09:03
 */
@Service("tbRuleModelService")
public class TbRuleModelServiceImpl extends ServiceImpl<TbRuleModelDao, TbRuleModel> implements TbRuleModelService {

}

