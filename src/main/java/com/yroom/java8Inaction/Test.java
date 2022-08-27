package com.yroom.java8Inaction;

import java.util.concurrent.Future;

/**
 * @author YROOM
 * @date 2022/5/2 9:38
 */
public class Test {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice1 = shop.getPriceAsynchronous("myPhone");
        long invocationTime = System.nanoTime() - start;
        System.out.println("Invocation returned after " + invocationTime + " ns");
        try{
            double price = futurePrice1.get();
            System.out.println("Price is " + price);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }

}




