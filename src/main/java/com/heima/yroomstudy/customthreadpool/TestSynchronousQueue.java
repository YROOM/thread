package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

import static java.lang.Thread.sleep;

/**
 * @author YROOM
 * @date 2022/8/28 10:18
 */
@Slf4j
public class TestSynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                log.info("putting---{}", 1);
                integers.put(1);
                log.info("putted---{}", 1);


                log.info("putting---{}", 2);
                integers.put(2);
                log.info("putted---{}", 2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        sleep(1);

        new Thread(() -> {
            try {
                log.info("taking--{}", 1);
                integers.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();


        sleep(1);

        new Thread(() -> {
            try {
                log.info("taking---{}", 2);
                integers.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t3").start();


    }


}
