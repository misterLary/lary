package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class CalculateController {


    static HashSet<Val<Integer>> valueSet = new HashSet<>();

    synchronized static void setData(Val<Integer> val){
        valueSet.add(val);
    }

    ThreadLocal<Val<Integer>> valThreadLocal = new ThreadLocal<Val<Integer>>(){
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> val = new Val<>();
            val.setValue(0);
            valThreadLocal.set(val);
            setData(val);
            return super.initialValue();
        }
    };

    @RequestMapping("/add")
    public void add() throws Exception{
        Val<Integer> val = valThreadLocal.get();
        val.setValue(val.getValue() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat(){
        return valueSet.stream().map(val -> val.getValue()).reduce((a,x)-> a + x).get();
    }

}
