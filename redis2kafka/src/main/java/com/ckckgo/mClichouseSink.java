package com.ckckgo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;


public class mClichouseSink extends RichSinkFunction<String> {
    private Connection conn;
    private Statement stat;

    @Override
    public void open(Configuration parameters) throws Exception {
        conn = DriverManager.getConnection("jdbc:clickhouse://localhost:8123", "default", "");
        stat = conn.createStatement();
    }

    @Override
    public void invoke(String value, Context context) throws Exception {
        // TODO Auto-generated method stub
        // super.invoke(value, context);
        if(value.length() < 100) {
            return;
        }
        String sql = String.format("INSERT INTO szt.szt_data VALUES ('%s');", value);
        stat.execute(sql);
        System.out.println(value);
    }

    // @Override
    // public void close() throws Exception {
    //     conn.close();
    // }

}
