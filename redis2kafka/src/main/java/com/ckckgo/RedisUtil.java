package com.ckckgo;

import java.time.Duration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisUtil
 */
public class RedisUtil {
    private static JedisPoolConfig poolConfig;
    static {
        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(20);
        // poolConfig.setMinIdle(20);
        // poolConfig.setBlockWhenExhausted(true);
        // // poolConfig.setMaxWait(Duration.ofSeconds(2));
        // poolConfig.setTestOnBorrow(true);
        // poolConfig.setTestOnReturn(true);
        // poolConfig.setTestWhileIdle(true);
    }
    private static JedisPool jedisPool;
    static {
        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }
    
    public static Jedis getJedisClient() {
        Jedis client = jedisPool.getResource();
        return client;
    }
}