package com.yroom.tongbu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YROOM
 * @date 2022/8/11 13:18
 */
public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " "+Thread.currentThread().getId()+Thread.currentThread().getName());
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->lockTest.func());
        executorService.execute(()->lockTest.func());
    }

}
