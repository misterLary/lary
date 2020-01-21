package com.cjb.thread.product_consumer;

public class Produce implements Runnable{

    Factory factory;
    String name;

    public Produce(Factory factory,String name) {
        this.factory = factory;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
//            try{
//                Thread.sleep(1000);
//                factory.produce(name,"product_" + System.currentTimeMillis());
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            factory.produce(name,"product_" + System.currentTimeMillis());
        }
    }
}
