package com.tension.rest.component.Impl;

import com.tension.rest.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版
 */
public class JedisClientSingle implements JedisClient {


    @Autowired
    private JedisPool jedisPool;


    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String rtn = jedis.set(key,value);
        jedis.close();
        return rtn;
    }

    public String get(String key) {

        Jedis jedis = jedisPool.getResource();
        String rtn = jedis.get(key);
        jedis.close();
        return rtn;
    }

    public Long hset(String key, String item, String value) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.hset(key,item,value);
        jedis.close();
        return rtn;
    }

    public String hget(String key, String item) {
        Jedis jedis = jedisPool.getResource();
        String rtn = jedis.hget(key,item);
        jedis.close();
        return rtn;
    }

    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.incr(key);
        jedis.close();
        return rtn;
    }

    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.decr(key);
        jedis.close();
        return rtn;
    }

    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.expire(key,second);
        jedis.close();
        return rtn;
    }

    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.ttl(key);
        jedis.close();
        return rtn;
    }

    public Long hdel(String key, String item) {
        Jedis jedis = jedisPool.getResource();
        Long rtn = jedis.hdel(key,item);
        jedis.close();
        return rtn;
    }
}
