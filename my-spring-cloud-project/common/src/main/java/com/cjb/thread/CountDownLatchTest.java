package com.cjb.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {



    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i =0;i<5;i++){
            new Thread(new MyTask(latch,i*1000,i)).start();
        }
        try {
            latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

 class MyTask implements Runnable {
    CountDownLatch latch;
    int delayTime; // Test
    int curThread;

    public MyTask(CountDownLatch latch, int delayTime, int curThread) {
        this.latch = latch;
        this.delayTime = delayTime;
        this.curThread = curThread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delayTime);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public static void main(String[] args) {
         AtomicInteger ai =new AtomicInteger(1);
         ai.incrementAndGet();
     }

}

interface Test{
    abstract void test();
}