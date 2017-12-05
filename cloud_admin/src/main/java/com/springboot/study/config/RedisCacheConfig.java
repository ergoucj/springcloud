package com.springboot.study.config;

import java.lang.reflect.Method;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.study.common.beans.RedisObjectSerializer;
import org.springframework.cache.CacheManager;

        import org.springframework.cache.annotation.CachingConfigurerSupport;

        import org.springframework.cache.annotation.EnableCaching;

        import org.springframework.cache.interceptor.KeyGenerator;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

        import org.springframework.data.redis.cache.RedisCacheManager;

        import org.springframework.data.redis.connection.RedisConnectionFactory;

        import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

        import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 缓存配置;
 *
 * 注意：RedisCacheConfig这里也可以不用继承 ：CachingConfigurerSupport，也就是直接一个普通的Class就好了；
 *
 * 这里主要我们之后要重新实现 key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
 *
 * 普通使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻烦。
 * @author Administratior
 */

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 自定义key. 这个可以不用
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            // This will generate a unique key of the class name, the method name
            //and all method parameters appended.
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            System.out.println("keyGenerator=" + sb.toString());
            return sb.toString();
        };
    }

    /**
     * 缓存管理器.
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
        return cacheManager;
    }


    /**
     * RedisTemplate缓存操作类,类似于jdbcTemplate的一个类;
     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
     * 自己的缓存类，比如：RedisStorage类;
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public  RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {

//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        //定义key序列化方式
//        //RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
//        //定义value的序列化方式
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        // 设置内容序列化类
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();

        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }


}
