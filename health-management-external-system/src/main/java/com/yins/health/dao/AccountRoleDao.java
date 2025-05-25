package com.yins.health.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 *@Author Mister
 *@Date 2025/3/27 10:05
 */
@Mapper
public interface AccountRoleDao {
    @Insert("insert into account_role(account_id,role_id) values (#{accountId},#{roleId})")
    void insertAccountRole(@Param("accountId") Long accountId, @Param("roleId") Long roleId);
}

