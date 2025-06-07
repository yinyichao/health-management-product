package com.yins.health.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yins.health.entity.TbDept;
import com.yins.health.entity.dto.DeptWithUsersDTO;
import com.yins.health.entity.dto.TbUserListDto;

import java.util.List;

/**
 * 部门表(TbDept)表服务接口
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
public interface TbDeptService extends IService<TbDept> {

    List<DeptWithUsersDTO> getRootDeptWithUsers(Integer taskId);

    List<TbUserListDto> userByName(String name);

    List<TbDept> deptByName(String name);
}

