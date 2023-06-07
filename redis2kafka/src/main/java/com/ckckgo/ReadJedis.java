package com.ckckgo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

/**
 * jedis = RedisUtil.getJedisClient();
        
        for (int i = 1; i < 1337; i++) {
            String value = jedis.get(i + "");
            
            JSONObject json = JSON.parseObject(value);
            JSONArray array = json.getJSONArray("data");
            if(array.size() != 1000) {
                System.err.println("---- array size error ---- i = " + i);
            }
            for (Object object : array) {
                String xStr = object.toString();
                JSONArray data = JSON.parseArray(xStr);
                if (data.size() != 11) {
                    System.err.println("----- data error ----- x = " + data.size());
                } else {
                    ctx.collect(xStr);
                }
            }
        }
    }
 */
public class ReadJedis {
    public static void main(String[] args) {
        Jedis mJedis = RedisUtil.getJedisClient();
        for (int i = 1; i < 2; i++) {
            String value = mJedis.get(i + "");
            JSONObject json = JSON.parseObject(value);
            JSONArray array = json.getJSONArray("data");
            for (Object object : array) {
                System.out.println(object);
            }
            

            // System.out.println(json);
            // System.out.println("value " + i + " :" + value);
        }
    }
}
