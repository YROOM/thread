package com.yroom.study;

/**
 * @author YROOM
 * @date 2022/5/1 17:14
 */
public class CreateThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("hello world111");
        });
        /**
         * 创建的时候，线程默认的状态是NEW,调用start方法后，状态变为RUNNABLE
         */
        System.out.println(thread.getState());
        /**这边线程需要调用一下start方法，让线程启动，如果直接调用了一个run方法，那么此时其实就是被当做一个普通的run方法调用而已。线程的状态并没有改变/
        /*thread.start();*/
        System.out.println(thread.getState());
        /**
         * 当线程通过new Thread()创建的时候,线程的名称是Thread-0
         */
        System.out.println(thread.getName());
        /**
         * 线程的创建并不是简单的new一个Thread对象，线程在new的时候，会再堆内存中出现一块空间，但是在栈内存中并没有出现，此时它只是一个简单的，普通的对象。
         * 那么我们需要告诉jvm这个对象是一个线程，而不是一个普通的对象。
         * 实现线程的方法有:继承Thread类，实现Runnable接口。
         * 其实在Thread类中，其本身也是实现了Runnable接口的，所以我们可以直接使用Thread类来创建线程。
         */
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId());

        thread.run();


        System.out.println("main thread end");
    }
}
