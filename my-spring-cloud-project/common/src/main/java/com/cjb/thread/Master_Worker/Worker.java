package com.cjb.thread.Master_Worker;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();

    protected Map<String,Object> resultMap = new ConcurrentHashMap<>();

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input){
        return input;
    }

    @Override
    public void run() {
        while (true){
            Object input = workQueue.poll();
            if(input == null) break;
            Object re = handle(input);
            resultMap.put(Integer.toString((input.hashCode())),re);
        }
    }
}
