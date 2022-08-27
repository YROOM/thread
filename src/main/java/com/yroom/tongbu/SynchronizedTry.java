package com.yroom.tongbu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YROOM
 * @date 2022/8/10 22:23
 */
public class SynchronizedTry {


    public void func2() {
        synchronized (SynchronizedTry.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " " + Thread.currentThread().getName());
            }
        }
    }


    public static void main(String[] args) {
        SynchronizedTry t1 = new SynchronizedTry();
        SynchronizedTry t2 = new SynchronizedTry();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(t1::func2);
        executorService.execute(t2::func2);


    }

}
