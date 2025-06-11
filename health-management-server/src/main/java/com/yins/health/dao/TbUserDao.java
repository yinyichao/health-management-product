package com.yins.health.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yins.health.entity.TbUser;
import com.yins.health.entity.dto.TbUserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户表;(TbUser)表数据库访问层
 *
 * @author yinyichao
 * @since 2025-05-26 10:41:07
 */
public interface TbUserDao extends BaseMapper<TbUser> {
    //@Select("select u.id,u.username from tb_user u inner join tb_user_dept d on u.id = d.user_id where d.dept_id = #{deptId}")
    @Select("select a.*,IF(t.id IS NOT NULL, 1, 0) as isCheck from (select u.id,u.username from tb_user u inner join tb_user_dept d on u.id = d.user_id where d.dept_id = #{deptId}) a left join tb_task_user t on a.id = t.user_id and t.task_id = #{taskId}")
    List<TbUserDto> selectTbUsersDto(@Param("deptId")int deptId, @Param("taskId")Integer taskId);

    //@Select("select u.id,u.username,0 as isCheck from tb_user u inner join tb_user_dept d on u.id = d.user_id where d.dept_id = #{deptId}")
    @Select("select a.*,IF(t.id IS NOT NULL, 1, 0) as isCheck from (select u.id,u.username from tb_user u inner join tb_user_dept d on u.id = d.user_id where d.dept_id = #{deptId}) a left join tb_task_user t on a.id = t.user_id")
    List<TbUserDto> selectTbUserDto(@Param("deptId")int deptId);
}

