package com;

import java.util.concurrent.*;

public class TestCompletionService {
    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1、创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        completionService.submit(new MyThreadt33(commandstr01));
        completionService.submit(new MyThreadt44(commandstr01));
        executorService.shutdown();
        System.out.println(completionService.take().get());
        System.out.println(completionService.take().get());
        System.out.println("131434");
    }
}

class MyThreadt33 implements Callable<String>{
    private String commandstr;          // 要运行的mingling
    public MyThreadt33(String commandstr) {
        this.commandstr = commandstr;
    }
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            Thread.sleep(200);
            sum += i;
            System.out.println("Mythread3: "+i);
        }
        return String.valueOf(sum+300000);
    }
}

class MyThreadt44 implements Callable<String> {
    private String commandstr;          // 要运行的mingling
    public MyThreadt44(String commandstr) {
        this.commandstr = commandstr;
    }
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 50; i++) {
            Thread.sleep(200);
            sum += i;
            System.out.println("Mythread4: "+i);
        }
        return String.valueOf(sum+400000);
    }
}
