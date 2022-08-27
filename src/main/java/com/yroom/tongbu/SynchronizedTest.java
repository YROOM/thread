package com.yroom.tongbu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author YROOM
 * @date 2022/8/10 21:22
 */
public class SynchronizedTest {

    public void synchronizeFun() {
        //同步了一个代码块
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前的线程" + Thread.currentThread().getName() + " " + i);
            }
        }
    }

    public synchronized void synchronizeFun2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前的线程" + Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest synTest1 = new SynchronizedTest();
        SynchronizedTest syncTest2 = new SynchronizedTest();
        SynchronizedTest sy3 = new SynchronizedTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(synTest1::synchronizeFun);
        executorService.execute(syncTest2::synchronizeFun);
        executorService.execute(sy3::synchronizeFun);
    }


}
