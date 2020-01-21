package com.cjb.thread.threadPool;

import java.util.List;
import java.util.Vector;

public class ThreadPool {

    private static ThreadPool instance = null;

    private List<PThread> idleThreads;

    private int threadCounter;

    private boolean isShutDown = false;

    public ThreadPool() {
        this.idleThreads = new Vector<>(5);
        threadCounter = 0;
    }

    public int getCreatedThreadsCount(){
        return threadCounter;
    }

    public synchronized static ThreadPool getInstance(){
        if(instance == null){
            instance = new ThreadPool();
        }
        return instance;
    }

    protected synchronized void repool(PThread repoolingThread){
        if(!isShutDown){
            idleThreads.add(repoolingThread);
        }else {
            repoolingThread.shutDown();
        }
    }

    public synchronized void shutDown(){
        isShutDown = true;
        for (int threadIndex = 0; threadIndex < idleThreads.size(); threadIndex++){
            PThread idleThread = idleThreads.get(threadIndex);
            idleThread.shutDown();
        }
    }

    public synchronized void start(Runnable target){
        PThread thread = null;
        if(idleThreads.size() > 0){
            int lastIndex = idleThreads.size() - 1;
            thread = idleThreads.get(lastIndex);
            idleThreads.remove(lastIndex);
            thread.setTarget(target);
        }else {
            threadCounter++;
            thread = new PThread("PThread #" + threadCounter,this,target);
            thread.start();
        }
    }


}
