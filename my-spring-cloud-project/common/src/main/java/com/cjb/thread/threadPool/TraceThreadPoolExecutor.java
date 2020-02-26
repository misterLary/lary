package com.cjb.thread.threadPool;

import java.util.concurrent.*;

/**
 * 通过重写线程池提交方法，添加异常处理
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(wrap(task,clientTrace(),Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task,clientTrace(),Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task,final Exception clientStack,String clientThreadName){
        return new Runnable() {
            @Override
            public void run() {
                try{
                    task.run();
                }catch (Exception e){
                    clientStack.printStackTrace();
                    throw e;
                }
            }
        };
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0L,TimeUnit.SECONDS,new SynchronousQueue<>());
        for ( int i = 0 ; i < 5 ; i++){
            pools.execute(new DivTask(100,i));
        }
    }

}
