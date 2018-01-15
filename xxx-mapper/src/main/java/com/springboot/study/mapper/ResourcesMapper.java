package com.springboot.study.mapper;

import com.springboot.study.base.MyMapper;
import com.springboot.study.domain.Resources;

import java.util.List;
import java.util.Map;
//@Mapper
public interface ResourcesMapper extends MyMapper<Resources> {

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String, Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}