package com.springboot.study.shiro;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/11/30
 *To change this template use File|Settings|File Templates.
 */
public class RedisCacheManager implements CacheManager {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
    @Resource
    private RedisTemplate redisTemplate;
    // fast lookup by name map
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    private String keyPrefix = "shiro_redis_cache:";


    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = (Cache)this.caches.get(name);
        if (c == null) {
            c = new RedisCache(redisTemplate, this.keyPrefix);
            this.caches.put(name, c);
        }

        return (Cache)c;
    }
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}