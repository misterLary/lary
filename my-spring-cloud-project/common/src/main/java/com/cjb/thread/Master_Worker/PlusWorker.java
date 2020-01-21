package com.cjb.thread.Master_Worker;

import java.util.Map;
import java.util.Set;

public class PlusWorker extends Worker{

    public Object handle(Object input){
        Integer i = (Integer) input;
        return i * i * i;
    }

    public static void main(String[] args) {
        Master m = new Master(new PlusWorker(),5);
        for (int i = 0; i < 100; i++){
            m.submit(i);
        }
        m.execute();
        int re = 0;
        Map<String,Object> resultMap = m.getResultMap();
        while (resultMap.size() > 0 || !m.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k : keys){
                key = k;
                break;
            }
            Integer i = null;
            if(key != null){
                i = (Integer) resultMap.get(key);
            }
            if(i != null){
                re += i;
            }
            System.out.println("re:  " + re);
            if(key != null){
                resultMap.remove(key);
            }
        }
    }

}
