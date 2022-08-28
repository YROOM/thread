package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YROOM
 * @date 2022/8/28 9:54
 */
@Slf4j(topic = "c.TestThreadPoolExecutors")
public class TestThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "yroomThreadPool" + t.getAndIncrement());
            }
        });


        executorService.execute(() -> {
            log.info("task1");
        });
        executorService.execute(() -> {
            log.info("task2");
        });
        executorService.execute(() -> {
            log.info("task3");
        });


    }
}
