package com.springboot.study.mapper;


import com.springboot.study.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MyMapper<User> {
}