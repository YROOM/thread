package com.heima.yroomstudy.customthreadpool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author YROOM
 * @date 2022/8/30 7:54
 */
@Slf4j
public class TestTimer {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        log.info("start...");
        /*pool.scheduleAtFixedRate(() -> {
            log.debug("running");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);*/

        //一定会等这个时间，等待这个delay的时间
        pool.scheduleWithFixedDelay(() -> {
            log.debug("running");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    private static void extracted2(ScheduledExecutorService pool) {
        pool.schedule(() -> {
            log.debug("task1");


            int i = 1 / 0;
            //下面的不会执行了
            log.info("我还是执行了");

        }, 1, TimeUnit.SECONDS);


        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);
    }

    public static void method1() {
        Timer timer = new Timer();


        TimerTask task1 = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("task 1");
                sleep(1000);

            }
        };


        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };

        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
    }


}
