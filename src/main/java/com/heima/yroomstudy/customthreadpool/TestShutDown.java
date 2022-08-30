package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author YROOM
 * @date 2022/8/29 22:50
 */
@Slf4j
public class TestShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        
        pool.submit(()->{
            log.debug("task 1 running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 1 finsh");
        });

        Future<Integer> submit = pool.submit(() -> {
            log.debug("task 2 running");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 2 finsh");
            return 3;
        });

        pool.submit(()->{
            log.debug("task 3 running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 3 finsh");
        });
        
        log.debug("shutdown");
        /*pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);*/
        List<Runnable> runnables = pool.shutdownNow();
        log.debug("other...{}",runnables);

    
    }
}
