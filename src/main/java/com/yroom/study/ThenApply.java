package com.yroom.study;

import java.util.concurrent.CompletableFuture;

/**
 * @author YROOM
 * @date 2022/5/2 11:35
 */
public class ThenApply {
    public static void main(String[] args) throws InterruptedException {
        SmallTool.printTimeAndThread("我小白，吃完饭了");
        SmallTool.printTimeAndThread("小白：服务员结账！！！");

     /*   CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员开始结账！！！");
            SmallTool.printTimeAndThread("服务员收钱100元");
            return "服务员收到了100元发票";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("我是会计：我要开票了！！！");
            return "我是会计：我开了100元发票";
        }));*/


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("服务员开始结账！！！");
            SmallTool.printTimeAndThread("服务员收钱100元");
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
                SmallTool.printTimeAndThread("我是会计：我要开票了！！！");
                return "我是会计：我开了100元发票";
            });
            return future3.join();
        });


        SmallTool.printTimeAndThread("我小白，晚上一起打游戏");
        SmallTool.printTimeAndThread(String.format("我小白收到发票了，溜溜球了", future2.join()));
    }
}
