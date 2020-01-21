package com.cjb.thread.Master_Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    protected Map<String,Thread> threadMap = new HashMap<>();
    protected Map<String,Object> resultMap = new ConcurrentHashMap<>();

    public boolean isComplete(){
        for (Map.Entry<String,Thread> entry:threadMap.entrySet()) {
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public void submit(Object job){
        workQueue.add(job);
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Master(Worker worker,int countWorker) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < countWorker ; i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    public void execute(){
        for (Map.Entry<String,Thread> entry:threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

}
