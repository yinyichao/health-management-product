package com.yins.health.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbLoanProductsDao;
import com.yins.health.entity.TbLoanProducts;
import com.yins.health.service.TbLoanProductsService;
import org.springframework.stereotype.Service;

/**
 * 贷款产品表(TbLoanProducts)表服务实现类
 *
 * @author yinyichao
 * @since 2025-05-13 11:31:18
 */
@Service("tbLoanProductsService")
public class TbLoanProductsServiceImpl extends ServiceImpl<TbLoanProductsDao, TbLoanProducts> implements TbLoanProductsService {

}

