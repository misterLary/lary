package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.jedis")
public class JedisProperties {

    /**Redis服务器主机ip*/
    private String host = "localhost";
    /**Redis服务器登录密码*/
    private String password;
    /**Redis服务器端口*/
    private int port = 6379;
    /**连接超时时间*/
    private int timeout = 2000;
    /**连接池配置*/
    private Pool pool = new Pool();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }
}
