package com.cjb.thread.futureTest;

public class MainTest {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
//        try{
//            Thread.sleep(1010);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        System.out.println("数据：" + data.getResult());
    }

}
