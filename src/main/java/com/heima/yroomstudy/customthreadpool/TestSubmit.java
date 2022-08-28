package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author YROOM
 * @date 2022/8/28 15:45
 */
@Slf4j
public class TestSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //  methodSubmit(executorService);

        //  methodInvokeAll(executorService);

        String result = executorService.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin1");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin2");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin3");
                    Thread.sleep(1000);
                    return "3";
                }
        ));
        
        log.info(result);


    }

    private static void methodInvokeAll(ExecutorService executorService) throws InterruptedException {
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin1");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin2");
                    Thread.sleep(1000);
                    return "2";
                },
                () -> {
                    log.debug("begin3");
                    Thread.sleep(1000);
                    return "3";
                }
        ));

        futures.forEach(value -> {
            try {
                log.info("{}", value.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }


    private static void methodSubmit(ExecutorService executorService) throws InterruptedException, ExecutionException {
        String running = executorService.submit(() -> {
            log.debug("running");
            Thread.sleep(10000);
            return "ok";
        }).get();


        log.info(running);
    }
}
