package site.keyu.askme.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import site.keyu.askme.pojo.Comment;

import java.util.List;

/**
 * @Author:Keyu
 */
@Component
public class RedisCacheUtil implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheUtil.class);
    private JedisPool pool;


    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/3");
    }

    public void putCathe(Object key,Object value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String json = JSONObject.toJSONString(value);
            jedis.set(String.valueOf(key.hashCode()),json);
//            logger.info("存储缓存 key:"+key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Object getCathe(Object key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String json = jedis.get(String.valueOf(key.hashCode()));
            List<Comment> value = JSON.parseArray(json,Comment.class);
//            if (value == null){
//                logger.info("未命中 key:"+key);
//            }
//            else{
//                logger.info("命中 key:"+key);
//            }

            return  value;
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Object removeCathe(Object key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return  jedis.expire(String.valueOf(key.hashCode()),0);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;

    }

    public int getCatheSize(){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            int size = Integer.valueOf(jedis.dbSize().toString());
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public void clearCathe(){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.flushDB();
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }



}
