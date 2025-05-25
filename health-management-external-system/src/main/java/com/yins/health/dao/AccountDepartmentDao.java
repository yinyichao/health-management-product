package com.yins.health.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 *TODO
 *@Author Mister
 *@Date 2025/3/27 10:19
 */
@Mapper
public interface AccountDepartmentDao {

    @Insert("insert into account_department(account_id,department_id) values(#{accountId},#{departmentId})")
    void insertAccountDepartment(@Param("accountId") Long accountId,@Param("departmentId")  Long departmentId);
}
