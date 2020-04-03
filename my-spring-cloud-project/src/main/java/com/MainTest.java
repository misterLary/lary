package com;

import java.io.File;

public class MainTest {

    public void test1() throws Exception{
        String str1 = "1";
        try{
            File file = new File("qqewq");
        }catch (Exception e){
            throw e;
        }
    }

    public static void main(String[] args) {

    }

}
