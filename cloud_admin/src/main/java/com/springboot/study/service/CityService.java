package com.springboot.study.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.springboot.study.domain.City;

/**
 * 城市业务逻辑接口类
 *
 *
 * @author bysocket
 * @date 07/02/2017
 */
public interface CityService {
    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City getCityById(Integer id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateCity(City city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    Long deleteCity(Long id);

    /**
     * 查询所有
     */
    Page listCity(City city);
}