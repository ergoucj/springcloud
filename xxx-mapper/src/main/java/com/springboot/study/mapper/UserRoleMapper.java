package com.springboot.study.mapper;

import com.springboot.study.base.MyMapper;
import com.springboot.study.domain.Role;
import com.springboot.study.domain.UserRole;

import java.util.List;
//@Mapper
public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);

    List<Role> listRoleByUid(Integer uid);
}