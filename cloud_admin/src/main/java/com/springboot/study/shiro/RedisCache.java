package com.springboot.study.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/12/4
 *To change this template use File|Settings|File Templates.
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private Logger logger;
    private RedisTemplate<K, V> cache;
    private String keyPrefix;
    private long globExpire = 30;

    public RedisCache(RedisTemplate cache) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.keyPrefix = "shiro_redis_session:";
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        } else {
            this.cache = cache;
        }
    }

    public RedisCache(RedisTemplate cache, String prefix) {
        this(cache);
        this.keyPrefix = prefix;
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    private K getCacheKey(Object k) {
        return (K) (this.keyPrefix + k);
    }

    @Override
    public V get(K key) throws CacheException {
        this.logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if (key == null) {
                return null;
            } else {
                this.cache.boundValueOps(getCacheKey(key)).expire(globExpire, TimeUnit.MINUTES);
                return this.cache.boundValueOps(getCacheKey(key)).get();

            }
        } catch (Throwable var4) {
            throw new CacheException(var4);
        }
    }


    @Override
    public V put(K key, V value) throws CacheException {
        this.logger.debug("根据key从存储 key [" + key + "]");
        try {
            this.cache.boundValueOps(getCacheKey(key)).set(value);
            return value;
        } catch (Throwable var4) {
            throw new CacheException(var4);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        this.logger.debug("从redis中删除 key [" + key + "]");

        try {
            V previous = this.get(key);
            this.cache.delete(this.getCacheKey(key));
            return previous;
        } catch (Throwable var3) {
            throw new CacheException(var3);
        }
    }

    @Override
    public void clear() throws CacheException {
        this.logger.debug("从redis中删除所有元素");
        this.cache.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return this.cache.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }
}