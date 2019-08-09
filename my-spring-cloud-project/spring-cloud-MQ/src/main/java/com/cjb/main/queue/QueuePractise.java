package com.cjb.main.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 队列方法的使用练习
 */
public class QueuePractise {

    public static void main(String[] args) {
        Queue queue = new ArrayBlockingQueue(5);
        int count = 0;
        while (count < 5){
            try{
                ((ArrayBlockingQueue) queue).add("1");
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("count: " + count);
            count++;
        }
    }

}
