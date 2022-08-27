package com.yroom.java8Inaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author YROOM
 * @date 2022/5/1 18:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private String name;


    public Future<Double> getPriceAsynchronous(String product) {


        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
      /*  CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;*/
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay(1000);
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }


    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
