package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbUserDeptDao;
import com.yins.health.entity.TbUserDept;
import com.yins.health.service.TbUserDeptService;
import org.springframework.stereotype.Service;

/**
 * 用户-部门关联表(TbUserDept)表服务实现类
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
@Service("tbUserDeptService")
public class TbUserDeptServiceImpl extends ServiceImpl<TbUserDeptDao, TbUserDept> implements TbUserDeptService {

}

