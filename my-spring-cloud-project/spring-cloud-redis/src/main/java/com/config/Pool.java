package com.config;

public class Pool {
    /**最大连接数, 默认8个*/
    private int maxTotal = 8;
    /**最大空闲连接数, 默认8个*/
    private int maxIdle = 8;
    /**最小空闲连接数, 默认0*/
    private int minIdle = 0;
    private long maxWaitMillis;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
}
