package com.yins.health.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yins.health.dao.TbDeptDao;
import com.yins.health.dao.TbUserDao;
import com.yins.health.dao.TbUserDeptDao;
import com.yins.health.entity.TbDept;
import com.yins.health.entity.TbUser;
import com.yins.health.entity.TbUserDept;
import com.yins.health.entity.dto.DeptWithUsersDTO;
import com.yins.health.entity.dto.TbUserDto;
import com.yins.health.entity.dto.TbUserListDto;
import com.yins.health.service.TbDeptService;
import com.yins.health.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门表(TbDept)表服务实现类
 *
 * @author yinyichao
 * @since 2025-06-05 18:39:50
 */
@Service("tbDeptService")
public class TbDeptServiceImpl extends ServiceImpl<TbDeptDao, TbDept> implements TbDeptService {
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private TbUserDeptDao tbUserDeptDao;

    // 获取部门及其下的所有用户信息
    public DeptWithUsersDTO getDeptWithUsers(int deptId,Integer taskId) {
        TbDept dept = this.getById(deptId);
        // 构造DTO对象
        DeptWithUsersDTO deptWithUsersDTO = new DeptWithUsersDTO();
        deptWithUsersDTO.setDeptName(dept.getName());

        // 获取部门下的所有用户
        // 1. 查询所有与该部门ID相关联的用户ID
        List<TbUserDto> users;
        if(taskId!=null) {
            users = tbUserDao.selectTbUsersDto(deptId,taskId);
        }else{
            users = tbUserDao.selectTbUserDto(deptId);
        }
        deptWithUsersDTO.setUsers(users);


        // 获取子部门并递归调用
        List<TbDept> subDepartments = this.list(new QueryWrapper<TbDept>().eq("parentid", deptId));
        List<DeptWithUsersDTO> subDeptDTOs = subDepartments.stream()
                .map(subDept -> getDeptWithUsers(subDept.getId(),taskId)) // 递归获取子部门
                .collect(Collectors.toList());

        deptWithUsersDTO.setSubDepartments(subDeptDTOs);

        return deptWithUsersDTO;
    }
    // 获取根部门树
    @Override
    public List<DeptWithUsersDTO> getRootDeptWithUsers(Integer taskId) {
        // 获取根部门，假设根部门的parentid为null或0
        List<TbDept> rootDepts = this.list(new QueryWrapper<TbDept>().eq("parentid", 1));

        // 递归获取所有部门及其用户信息
        return rootDepts.stream()
                .map(dept -> getDeptWithUsers(dept.getId(),taskId))
                .collect(Collectors.toList());
    }

    @Override
    public List<TbUserListDto> userByName(String name) {
        List<TbUser> tbUsers = tbUserDao.selectList(new LambdaQueryWrapper<TbUser>().eq(TbUser::getIsAdmin,0).like(TbUser::getUsername, "%" + name + "%"));
        List<TbUserListDto> list = CommonUtil.convert(tbUsers,TbUserListDto.class);
        // 循环每个用户，查询关联的部门数据
        for (TbUserListDto user : list) {
            // 使用关联表 tb_user_dept 来查询用户关联的部门
            List<TbDept> deptList = new ArrayList<>();
            List<Integer> deptIds = tbUserDeptDao.selectList(
                    new LambdaQueryWrapper<TbUserDept>()
                            .eq(TbUserDept::getUserId, user.getId())
                            .select(TbUserDept::getDeptId)
            ).stream().map(TbUserDept::getDeptId).collect(Collectors.toList());

            if (!deptIds.isEmpty()) {
                deptList = baseMapper.selectList(
                        new LambdaQueryWrapper<TbDept>()
                                .in(TbDept::getId, deptIds)
                );
                user.setDeptList(deptList);
            }
        }
        return list;
    }

    @Override
    public List<TbDept> deptByName(String name) {
        List<TbDept> tbDepts = baseMapper.selectList(new LambdaQueryWrapper<TbDept>().like(TbDept::getName, "%" + name + "%"));
        return tbDepts;
    }
}

