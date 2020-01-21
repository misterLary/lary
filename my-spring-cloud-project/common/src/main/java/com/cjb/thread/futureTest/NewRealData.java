package com.cjb.thread.futureTest;

import java.util.concurrent.Callable;

public class NewRealData implements Callable<String> {

    protected String para = "";

    public NewRealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++){
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
