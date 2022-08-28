package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author YROOM
 * @date 2022/8/28 10:44
 */
@Slf4j
public class TestExecutors {
    public static void main(String[] args) {

        test();
    }

    public static void test() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor executorService2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
        
        executorService.execute(() -> {
            log.info("1");
            int i = 1 / 0;

        });
        
        
        executorService.execute(()->{
            log.info("2");
        });

        executorService.execute(()->{
            log.info("3");
        });


    }


}
