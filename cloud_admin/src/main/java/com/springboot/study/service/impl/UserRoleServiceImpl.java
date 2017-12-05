package com.springboot.study.service.impl;

import com.springboot.study.domain.Role;
import com.springboot.study.domain.UserRole;
import com.springboot.study.mapper.UserRoleMapper;
import com.springboot.study.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by yangqj on 2017/4/26.
 */
@Service("serRoleService")
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addUserRole(UserRole userRole) {
        //删除
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userRole.getUserid());
        mapper.deleteByExample(example);
        //添加
        String[] roleids = userRole.getRoleid().split(",");
        for (String roleId : roleids) {
            UserRole u = new UserRole();
            u.setUserid(userRole.getUserid());
            u.setRoleid(roleId);
            mapper.insert(u);
        }

    }

    @Override
    public List<Role> listRoleByUid(Integer uid) {
        return userRoleMapper.listRoleByUid(uid);
    }
}
