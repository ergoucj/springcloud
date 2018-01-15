package com.springboot.study.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 城市实体类
 *
 * @author Administratior
 */
@Data
@ApiModel(value = "City", description = "城市实体类")
public class City extends BaseEntity  {


    /**
     * 省份编号
     */
    @ApiModelProperty(value = "省份编号")
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;

    private Integer version;

    private HashMap<String,Object> map;

    private String[] ids;

    @Override
    public String toString() {
        return "City{" +
                "id=" +this.getId() +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}