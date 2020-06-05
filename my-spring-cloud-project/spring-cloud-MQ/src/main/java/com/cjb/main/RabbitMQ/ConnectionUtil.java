package com.cjb.main.RabbitMQ;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionUtil {

    public static Connection getConnection(String virtualHost) throws Exception {
        if(StringUtils.isEmpty(virtualHost)){
            virtualHost = "/";
        }
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost(virtualHost);
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}

