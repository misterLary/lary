package com.cjb.thread.product_consumer;

public class MainTest {

    public static void main(String[] args) {
        Factory factory = new Factory();
        Thread produce1 = new Thread(new Produce(factory,"生产者1"));
        Thread consumer1 = new Thread(new Consumer(factory,"消费者1"));
        Thread produce2 = new Thread(new Produce(factory,"生产者2"));
        Thread consumer2 = new Thread(new Consumer(factory,"消费者2"));
        Thread produce3 = new Thread(new Produce(factory,"生产者3"));
        Thread consumer3 = new Thread(new Consumer(factory,"消费者3"));
        produce1.start();
        consumer1.start();
        consumer2.start();
        produce2.start();
        produce3.start();
        consumer3.start();
    }

}
