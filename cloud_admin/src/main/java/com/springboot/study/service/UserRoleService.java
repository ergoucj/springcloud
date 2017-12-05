package com.springboot.study.service;

import com.springboot.study.domain.Role;
import com.springboot.study.domain.UserRole;

import java.util.List;

/**
 * Created by yangqj on 2017/4/26.
 */
public interface UserRoleService extends IService<UserRole> {

    public void addUserRole(UserRole userRole);

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    List<Role> listRoleByUid(Integer id);
}
