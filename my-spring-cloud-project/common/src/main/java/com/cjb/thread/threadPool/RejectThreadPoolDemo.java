package com.cjb.thread.threadPool;

import java.util.concurrent.*;

public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable{

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                     System.out.println(r.toString() + " is discard");
            }
        }){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行" + ((MyTask)r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行结束" + ((MyTask)r).getName());
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };
        for (int i = 0 ; i < Integer.MAX_VALUE ; i++ ){
            MyTask task = new MyTask("task_"+i);
            es.execute(task);
            Thread.sleep(10);
        }
    }

}
