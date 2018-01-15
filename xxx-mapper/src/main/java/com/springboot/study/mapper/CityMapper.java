package com.springboot.study.mapper;

import com.github.pagehelper.Page;
import com.springboot.study.domain.City;
import org.apache.ibatis.annotations.Param;
/**
 * @author Administratior
 */
//@Mapper//声明成mybatis Dao层的Bean，也可以在配置类上使用@MapperScan("com.springboot.study.mapper")注解声明
public interface CityMapper {
    /**
     * 获取城市信息列表
     *
     * @return
     */
    Page<City> listCity();
    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City getCityById(@Param("id") Integer id);

    /**
     *保存城市
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     *更新城市
     * @param city
     * @return
     */
    Long updateCity(City city);

    /**
     *删除城市
     * @param id
     * @return
     */
    Long deleteCity(Long id);
}

