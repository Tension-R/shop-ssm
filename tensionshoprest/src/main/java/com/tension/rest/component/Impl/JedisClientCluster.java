package com.tension.rest.component.Impl;

import com.tension.rest.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * 集群版
 */
public class JedisClientCluster implements JedisClient{

    @Autowired
    private JedisCluster jedisCluster;

    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public Long hset(String key, String item, String value) {
        return jedisCluster.hset(key,item,value);
    }

    public String hget(String key, String item) {
        return hget(key,item);
    }

    public Long incr(String key) {
        return incr(key);
    }

    public Long decr(String key) {
        return decr(key);
    }

    public Long expire(String key, int second) {
        return expire(key,second);
    }

    public Long ttl(String key) {
        return ttl(key);
    }

    public Long hdel(String key, String item) {
        return jedisCluster.hdel(key,item);
    }
}
