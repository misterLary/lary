package com.cjb.thread.product_consumer;

public class Consumer implements Runnable{

    Factory factory;
    String name;

    public Consumer(Factory factory,String name) {
        this.factory = factory;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
//            try{
//                Thread.sleep(1000);
//                factory.get(name);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            factory.get(name);
        }
    }
}
