package com;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
public class MainTest {
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//    }

    public static void main(String[] args) {
        float f1 = 6.5f;
        float f2 = 1.4f;
        System.out.println(f1 + f2);
        BigDecimal bigDecimal = new BigDecimal("21321");
        System.out.println(bigDecimal.intValue());
        Map map = new HashMap();
        map.put("","");
    }

}