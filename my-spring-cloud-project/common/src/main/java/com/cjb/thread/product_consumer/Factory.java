package com.cjb.thread.product_consumer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Factory{

    ReentrantLock reentrantLock = new ReentrantLock();
    Condition produceCondition = reentrantLock.newCondition();
    Condition getCondition = reentrantLock.newCondition();

    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public void produce(String userName,String str){
        System.out.println("生产者["+userName + "] 等待进入");
        reentrantLock.lock();
        System.out.println("生产者["+userName + "] 进来了");
        try{
            while(queue.peek() != null){
                System.out.println("生产者["+userName + "]在等待生产");
                produceCondition.await();
                System.out.println("生产者["+userName + "]开始生产");
            }
            queue.offer(str);
            System.out.println("生产者[" + userName +"] 生产了：" + str);
            getCondition.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("生产者["+userName + "] 出去了");
            reentrantLock.unlock();
        }
    }

    public void get(String userName){
        System.out.println("消费者["+userName + "] 等待进入");
        reentrantLock.lock();
        System.out.println("消费者["+userName + "] 进来了");
        try{
            while (queue.peek() == null){
                System.out.println("消费者[" + userName +"] 在等待消费");
                getCondition.await();
                System.out.println("消费者[" + userName +"] 开始消费");
            }
//            Thread.sleep(1000);
            System.out.println("消费者[" + userName +"] 获得了：" +queue.poll());
            produceCondition.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("消费者["+userName + "] 出去了");
            reentrantLock.unlock();
        }
    }

}
