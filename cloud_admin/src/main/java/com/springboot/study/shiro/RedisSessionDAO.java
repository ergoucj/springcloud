package com.springboot.study.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/***
 * Created by IntelliJ IDEA.
 *@author :Administratior
 *@date:2017/11/30
 *To change this template use File|Settings|File Templates.
 *
 * redis实现共享session
 */
@Component
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    @Resource
    private RedisTemplate redisTemplate;

    private String keyPrefix = "shiro_redis_session:";
    //    // session 在redis过期时间是30分钟30*60
    private static long expireTime = 1800;

    public RedisSessionDAO() {
    }

    // 创建session
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        logger.debug("创建session");
        if (session != null && session.getId() != null) {
            session.setTimeout((long) (expireTime*1000));
            String key = keyPrefix + session.getId().toString();
            redisTemplate.opsForValue().set(key , session);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } else {
            logger.error("session or session id is null");
        }
    }

    public void update(Session session) throws UnknownSessionException {
        logger.debug("update() ");
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        logger.debug("删除session:{}", session);
        if (session != null && session.getId() != null) {
//            this.redisManager.del(this.getByteKey(session.getId()));
            redisTemplate.delete(keyPrefix + session.getId().toString());
        } else {
            logger.error("session or session id is null");
        }
    }

    public Collection<Session> getActiveSessions() {
//        Set<Session> sessions = new HashSet();
//        Set<byte[]> keys = this.redisTemplate.keys(this.keyPrefix + "*");
//        if (keys != null && keys.size() > 0) {
//            Iterator i$ = keys.iterator();
//
//            while (i$.hasNext()) {
//                byte[] key = (byte[]) i$.next();
//                Session s = (Session) SerializeUtils.deserialize(this.redisManager.get(key));
//                sessions.add(s);
//            }
//        }

        return redisTemplate.keys(keyPrefix + "*");
    }


    protected Session doReadSession(Serializable sessionId) {
        logger.debug("doReadSession() ");
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            Session s = (Session) redisTemplate.opsForValue().get(keyPrefix + sessionId.toString());
            return s;
        }
    }

    private byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }


    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
//
//    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
//
//    // session 在redis过期时间是30分钟30*60
//    private static long expireTime = 1800;
//
//    private static String prefix = "shiro_redis_session:";
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//
//    // 创建session，保存到数据库
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = this.generateSessionId(session);
//        this.assignSessionId(session, sessionId);
//        this.saveSession(session);
//        return sessionId;
//    }
//
//
//    private void saveSession(Session session) throws UnknownSessionException {
//        logger.debug("创建session");
//        if (session != null && session.getId() != null) {
//            logger.debug("创建session:{}", session.getId());
//            redisTemplate.opsForValue().set(prefix + session.getId().toString(), session);
//        } else {
//            logger.error("session or session id is null");
//        }
//    }
//
//    // 获取session
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        logger.debug("获取session:{}", sessionId);
//        if (sessionId == null) {
//            logger.error("session id is null");
//            return null;
//        }
//        return (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
//    }
//
//    // 更新session的最后一次访问时间
//    @Override
//    public void update(Session session) {
//        logger.debug("update() 获取 session:{}", session.getId());
//        String key = prefix + session.getId().toString();
//        if (!redisTemplate.hasKey(key)) {
//            redisTemplate.opsForValue().set(key, session);
//        }
//        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
//    }
//
//
//
//    // 删除session
//    @Override
//    public void delete(Session session) {
//        logger.debug("删除session:{}", session);
//        if (session==null){
//            return;
//        }
//        logger.debug("删除session:{}", session.getId());
//        redisTemplate.delete(prefix + session.getId().toString());
//    }
//
//
//    @Override// 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，可以在将session加入redis时指定一个session前缀，统计的时候则使用keys("session-prefix*")的方式来模糊查找redis中所有的session集合
//    public Collection<Session> getActiveSessions() {
//        System.out.println("==============getActiveSessions=================");
//        return redisTemplate.keys(prefix+"*");
//    }
