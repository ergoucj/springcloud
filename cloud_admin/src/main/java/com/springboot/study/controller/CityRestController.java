package com.springboot.study.controller;

import com.springboot.study.common.beans.EasyUIDataGrideResult;
import com.springboot.study.common.beans.PageResultBean;
import com.springboot.study.common.beans.ResultBean;
import com.springboot.study.domain.City;
import com.springboot.study.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Administratior
 */
@CrossOrigin
@RestController
public class CityRestController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(CityRestController.class);

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "查询所有", httpMethod = "GET")
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "Array",paramType = "query")
    public PageResultBean findAllCIdy(String[] ids, HttpServletRequest request){
//        String ipFromRequest = IpUtils.getIpFromRequest(request);
//        System.out.println(ipFromRequest);
//        String format = MessageFormat.format("https://api.map.baidu.com/location/ip?ak={0}&ip={1}", "F454f8a5efe5e577997931cc01de3974",ipFromRequest);
//        NetWorkHelper netWorkHelper = new NetWorkHelper();
//        String httpsResponse = netWorkHelper.getHttpsResponse(format, "");
//        JSONObject json = JSON.parseObject(httpsResponse);
//        System.out.println(((JSONObject) json.get("content")).get("address"));
        System.out.println(getRemortIP(request));
        return new PageResultBean();
    }

    @ApiOperation(value = "Easyui查询所有", httpMethod = "GET")
    @RequestMapping(value = "/api/city/admin", method = RequestMethod.GET)
    public EasyUIDataGrideResult findAllCIdyEasy(@ModelAttribute City city) {
        return switchEasyUIResult(cityService.listCity(city));
    }


    @ApiOperation(value = "根据id查找", httpMethod = "GET")
    @ApiImplicitParams(value ={@ApiImplicitParam(name = "id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "num", required = false, dataType = "Integer", paramType = "query")})
    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public ResultBean findOneCity(@PathVariable("id") Integer id, Integer num) {
        LOG.info("id:"+id);
        return new ResultBean(cityService.getCityById(id));
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    @ApiImplicitParam(name = "city", value = "用户详细实体user", required = true, dataType = "City")
    public ResultBean createCity(@RequestBody City city) {
        return new ResultBean(cityService.saveCity(city));
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public ResultBean modifyCity(@RequestBody City city) {
        return new ResultBean(cityService.updateCity(city));
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public ResultBean modifyCity(@PathVariable("id") Long id) {
        return new ResultBean(cityService.deleteCity(id));
    }

    @RequestMapping(value = "/api/map", method = RequestMethod.GET)
    public void modifyCity1(City city) {
        System.out.println();
    }
    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String[] ipArray = ip.split(",");
        if(ipArray != null && ipArray.length > 1){
            return ipArray[0];
        }
        return ip;
    }
}