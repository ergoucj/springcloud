package com.springboot.study.controller;

import com.springboot.study.domain.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administratior
 */
@RestController
@RequestMapping("/rest/adxSspFinanceManagement")
public class AdxSspFinanceManagementController {

    private static final Logger LOG = LoggerFactory.getLogger(AdxSspFinanceManagementController.class);


    /**
     * 根据id查找
     *
     * @param id
     * @return
     * @throws
     * @date 2016年9月29日 下午7:09:03
     */
    @ApiOperation(value = "根据id查找", httpMethod = "POST")
//    @ApiImplicitParams({@ApiImplicitParam(name = "id", required = true, dataType = "Integer", paramType = "path")})
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.POST)
    public String findById(@PathVariable("id") Integer id) {
        LOG.info("id:{}", id);
        return "success";
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<City> getUserList() {
        List<City> r = new ArrayList<City>();
        return r;
    }
}
