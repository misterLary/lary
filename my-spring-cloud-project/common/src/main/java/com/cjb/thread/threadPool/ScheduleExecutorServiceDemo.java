package com.cjb.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduleExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);//执行定时任务
        ses.scheduleAtFixedRate(new Runnable() {//任务执行完，下一次任务紧接着执行
            @Override
            public void run() {
                try{
                    Thread.sleep(8000);
                    System.out.println(System.currentTimeMillis()/1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },0,2,TimeUnit.SECONDS);
    }

    public void test(){
        ExecutorService ses = Executors.newScheduledThreadPool(10);
        ses = Executors.newFixedThreadPool(10);
        ses = Executors.newSingleThreadExecutor();
    }



}
