package com.heima.yroomstudy.customthreadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author YROOM
 * @date 2022/8/30 7:26
 */
@Slf4j
public class TestStarvation {
    
    
    
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ExecutorService cookpool = Executors.newFixedThreadPool(1);
        
        pool.execute(()->{
            log.info("处理点菜。。。。。。。");
            Future<String> submit = cookpool.submit(() -> {
                log.debug("做菜");
                return "宫保鸡丁";
            });
            try {
                String s = submit.get();
                log.info("上菜{}",s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            log.info("处理点菜。。。。。。。");
            Future<String> submit = cookpool.submit(() -> {
                log.debug("做菜");
                return "宫保鸡丁2";
            });

            try {
                String s = submit.get();
                log.info("上菜{}",s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } 

        });

        pool.execute(()->{
            log.info("处理点菜。。。。。。。");
            Future<String> submit = cookpool.submit(() -> {
                log.debug("做菜");
                return "宫保鸡丁3";
            });

            try {
                String s = submit.get();
                log.info("上菜{}",s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });
        
        
        
    }
}
