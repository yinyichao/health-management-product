package com.yins.health.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeptWithUsersDTO {
    private String deptName;
    private List<TbUserDto> users;
    private List<DeptWithUsersDTO> subDepartments;

}