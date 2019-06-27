package com.cjb.util.test;

import com.cjb.Model.UserModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainTest {

    public static void main(String[] args) {

        test2();

    }

    public static void test1(){
        Set<UserModel> list1 = new HashSet<UserModel>();
        UserModel userModel = new UserModel();
        userModel.setUserName("a");
        list1.add(userModel);
        UserModel userMode2 = new UserModel();
        userMode2.setUserName("b");
        list1.add(userMode2);
        Set<UserModel> list2 = new HashSet<UserModel>();
        UserModel userMode3 = new UserModel();
        userMode3.setUserName("c");
        list2.add(userMode3);
        UserModel userMode4 = new UserModel();
        userMode4.setUserName("d");
        list2.add(userMode4);
    }

    public static void test2(){
        Set<UserModel> list1 = new HashSet<UserModel>();
        UserModel userModel = new UserModel();
        userModel.setUserName("a");
        list1.add(userModel);
        UserModel userMode2 = new UserModel();
        userMode2.setUserName("b");
        list1.add(userMode2);
        Set<UserModel> list2 = new HashSet<UserModel>();
        UserModel userMode3 = new UserModel();
        userMode3.setUserName("c");
        list2.add(userMode3);
        UserModel userMode4 = new UserModel();
        userMode4.setUserName("d");
        list2.add(userMode4);

        System.out.println("====求交集===");

        List<UserModel> list=list1.stream().filter(t->list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        System.out.println("====求差集===");
        list=list1.stream().filter(t-> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(t-> System.out.println(t.getUserName()));

        System.out.println("====求并集===");

        list.addAll(list1);
        list.addAll(list2);
        list=list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

}
