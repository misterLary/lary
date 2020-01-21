package com.cjb.thread.futureTest.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RealData implements Callable<String>{

    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10 ; i ++){
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<>(new RealData("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(future);
        System.out.println("请求完毕");
        try{
//            Thread.sleep(2000);
            System.out.println("数据：" + future.get());
            System.out.println("123");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
