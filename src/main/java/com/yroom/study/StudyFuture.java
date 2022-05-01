package com.yroom.study;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author YROOM
 * @date 2022/5/1 16:46
 */
public class StudyFuture {


    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("执行任务");
            return "hello";
        });
        Runnable runnable= () -> System.out.println("执行任务");

        FutureTask<String> futureTask1 = new FutureTask<>(runnable,new String());



        Thread thread = new Thread(futureTask);
        thread.start();
        try{
            System.out.println(futureTask1.get(5, TimeUnit.SECONDS));
            System.out.println(futureTask.get());
            System.out.println(futureTask.isDone());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
