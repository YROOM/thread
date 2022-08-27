package com.yroom.study;

import java.util.concurrent.CompletableFuture;

/**
 * @author YROOM
 * @date 2022/5/2 11:16
 */
public class TheCombine {
    public static void main(String[] args) {
        System.out.println("线程名称：" + Thread.currentThread().getName() + "我是小白，我现在进来餐厅了"+System.currentTimeMillis());
        System.out.println("线程名称：" + Thread.currentThread().getName() + "我是小白，我现在开始点菜"+System.currentTimeMillis());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "我是服务员，我来给小白点菜"+System.currentTimeMillis());
            return "红烧肉";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "我是厨师，我来给小白做菜，做的菜是" +  + System.currentTimeMillis());
            return "好了，小白，我们去吃饭了";
        }),(s,s2)->{
            System.out.println("线程名称：" + Thread.currentThread().getName() + "我是服务员，我来给小白上菜，上菜的菜是"+s+System.currentTimeMillis());
            return s;
        });
    }
}
