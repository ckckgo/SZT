package com.ckckgo;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

public class mRedisSourceFun implements SourceFunction<String> {

    private Jedis jedis;
    private boolean isRunning = true;

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        jedis = RedisUtil.getJedisClient();
        
        for (int i = 1; i < 1337; i++) {
            String value = jedis.get(i + "");
            
            JSONObject json = JSON.parseObject(value);
            JSONArray array = json.getJSONArray("data");
            if(array.size() != 1000) {
                System.err.println("---- array size error ---- i = " + i);
            }
            for (Object object : array) {
                String xStr = object.toString();
                JSONObject data = JSON.parseObject(xStr);
                if (data.size() != 11) {
                    System.err.println("----- data error ----- x = " + data.size());
                } else {
                    ctx.collect(xStr);
                }
            }
        }
    }
    @Override
    public void cancel() {
        isRunning = false;
        if (jedis != null) {
            jedis.close();
        }
    }
    
    
}
