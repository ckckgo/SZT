package com.ckckgo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        File file = new File("/Users/HeHongling/Developer/Bigdata/rawdata/record.json");
        List<String> records = FileUtils.readLines(file);

        Jedis jedis = new Jedis("localhost", 6379);
        for (String record : records) {
            JSONObject jsonObject = JSON.parseObject(record);
            String key = String.valueOf(jsonObject.getIntValue("page"));
            jedis.set(key, record);
            System.out.println("key: " + key);
        }
        jedis.close();
    }
}
