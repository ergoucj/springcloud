package com.springboot.study.mapper;

import com.springboot.study.base.MyMapper;
import com.springboot.study.domain.Role;

import java.util.List;

//@Mapper
public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}