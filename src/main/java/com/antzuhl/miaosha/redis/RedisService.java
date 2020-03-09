package com.antzuhl.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisConfig redisConfig;

    public <T> T get(String key, Class<T> clazz){
        Jedis jedis = null;
        T t = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            t = stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
        return t;
    }

    public <T> Boolean set(String key, T value){
        Jedis jedis = null;
        T t = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value==null) {
            return null;
        }
        return JSON.toJSONString(value);
    }


    private <T> T stringToBean(String str, Class<T> clazz) {
        return JSON.toJavaObject(JSON.parseObject(str), clazz);
    }

    private void returnToPool(Jedis jedis) {
        if (jedis!=null) {
            jedis.close();
        }
    }

    @Bean
    public JedisPool getJedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(1);
//        return new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), 2000, redisConfig.getPassword());
        return new JedisPool(poolConfig, "127.0.0.1", 6379, 2000, "root");
    }
}
