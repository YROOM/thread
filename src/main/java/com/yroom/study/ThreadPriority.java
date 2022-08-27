package com.yroom.study;

/**
 * @author YROOM
 * @date 2022/5/3 10:54
 */
public class ThreadPriority {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());

        Thread thread = new Thread(() -> {
            System.out.println("thread priority is " + Thread.currentThread().getPriority());
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.getPriority());
    }
}
