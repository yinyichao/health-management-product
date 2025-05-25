package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbTalentPolicyDao;
import com.yins.health.entity.TbTalentPolicy;
import com.yins.health.service.TbTalentPolicyService;
import org.springframework.stereotype.Service;

/**
 * 人才政策主表(TbTalentPolicy)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-13 11:17:57
 */
@Service("tbTalentPolicyService")
public class TbTalentPolicyServiceImpl extends ServiceImpl<TbTalentPolicyDao, TbTalentPolicy> implements TbTalentPolicyService {

}

