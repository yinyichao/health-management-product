package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yins.health.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author my project - Darius,
 * @since 2025-01-18
 */
@Mapper
public interface AccountDao extends BaseMapper<Account> {
}
