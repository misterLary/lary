package com.cjb.single;

/**
 * 第一种
 * 只要加载了类，就创建了对应的对象
 */
public class Singleton {

    private Singleton(){

    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

}

/**
 * 第二种
 * 延迟加载
 */
class Singleton1{

    private Singleton1(){}

    private static Singleton1 instance = null;

//    public static synchronized Singleton1 getInstance(){
//        if(instance == null){
//            instance = new Singleton1();
//        }
//        return instance;
//    }

    public static Singleton1 getInstance(){
        if(instance==null){//第一次校验，如果创建成功则不需要走代码块，所以不需要做同步处理
            synchronized(Singleton1.class){
                if(instance==null){//第二次校验，在对象未创建时，此时的判断必须保证只能一个线程进入，否则可能会在第一个线程进入未创建完成，紧接着第二个线程进入再进行构建
                    instance=new Singleton1();
                }
            }
        }
        return instance;
    }

}

/**
 * 使用内部类
 */
class Singleton3 {

    private Singleton3(){}

    private static class SingletonHolder{
        private static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return SingletonHolder.singleton3;
    }

}