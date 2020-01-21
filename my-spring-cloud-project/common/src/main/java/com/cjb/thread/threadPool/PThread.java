package com.cjb.thread.threadPool;



public class PThread extends Thread{

    private ThreadPool pool;

    private Runnable target;

    private boolean isShutDown = false;

    private boolean isIDle = false;

    public PThread(String name,ThreadPool pool, Runnable target) {
        super(name);
        this.pool = pool;
        this.target = target;
    }

    public Runnable getTarget(){
        return target;
    }

    public boolean isIDle() {
        return isIDle;
    }

    @Override
    public void run() {
        while (!isShutDown){
            isIDle = false;
            if(target != null){
                target.run();
            }
            isIDle = true;
            try{
                pool.repool(this);
                synchronized (this){
                    wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            isIDle = false;
        }
    }

    public synchronized void setTarget(Runnable newTarget){
        target = newTarget;
        notifyAll();
    }

    public synchronized void shutDown(){
        isShutDown = true;
        notifyAll();
    }

}
