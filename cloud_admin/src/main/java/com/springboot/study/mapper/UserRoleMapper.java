package com.springboot.study.mapper;

import com.springboot.study.domain.Role;
import com.springboot.study.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<Integer> findUserIdByRoleId(Integer roleId);

    List<Role> listRoleByUid(Integer uid);
}