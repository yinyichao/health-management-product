package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbDeptDao;
import com.yins.health.entity.TbDept;
import com.yins.health.service.TbDeptService;
import org.springframework.stereotype.Service;

/**
 * 部门表(TbDept)表服务实现类
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
@Service("tbDeptService")
public class TbDeptServiceImpl extends ServiceImpl<TbDeptDao, TbDept> implements TbDeptService {

}

