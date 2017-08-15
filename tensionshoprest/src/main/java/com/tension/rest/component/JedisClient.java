package com.tension.rest.component;

import org.apache.commons.lang3.StringUtils;

public interface JedisClient {

    String set(String key, String value);

    String get(String key);

    Long hset(String key, String item, String value);

    String hget(String key, String item);

    Long incr(String key);

    Long decr(String key);

    //设置过期时间
    Long expire(String key, int second);

    //查看过期时间 -1为永久
    Long ttl(String key);

    Long hdel(String key, String item);
}
