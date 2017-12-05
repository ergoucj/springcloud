package com.springboot.study.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Administratior
 */
public class BeanUtil<BeanType> {
    private static final Logger LOGGER = Logger.getLogger(BeanUtil.class);


    public Object convertMapToBean(Object object, Class<BeanType> beanClass) {
        return object instanceof List ? this.convertMapListToBeanList((List)object, beanClass) : this.convertOneMapListToOneBean((Map)object, beanClass);
    }

    public List<BeanType> convertMapListToBeanList(List<Map> mapList, Class<BeanType> beanClass) {
        List<BeanType> resultList = new ArrayList();

        for(int i = 0; i < mapList.size(); ++i) {
            Map map = (Map)mapList.get(i);
            BeanType bean = this.convertOneMapListToOneBean(map, beanClass);
            resultList.add(bean);
        }

        return resultList;
    }

    public BeanType convertOneMapListToOneBean(Map map, Class<BeanType> beanClass) {
        try {
            List removeKey = new ArrayList();
            Set keySet = map.keySet();
            keySet.forEach((key) -> {
                if (map.get(key) == null) {
                    removeKey.add(key);
                }

            });
            removeKey.forEach((key) -> {
                map.remove(key);
            });
            BeanType bean = beanClass.newInstance();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (Exception var6) {
            LOGGER.error("com.springboot.study.util.BeanUtil.convertOneMapListToOneBean(mapVar:util.HashMap): BeanType", var6);
            throw new RuntimeException("将map转化为bean出错");
        }
    }
}
