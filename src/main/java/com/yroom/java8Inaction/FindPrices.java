package com.yroom.java8Inaction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author YROOM
 * @date 2022/5/2 10:06
 */
public class FindPrices {
    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        List<CompletableFuture<String>> practices = shops.stream().map(
                shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice("my favorite product")
                )).collect(Collectors.toList());

        practices.stream().map(CompletableFuture::join).collect(Collectors.toList());

    }


    private static List<String> findPrices(String product) {

        return null;
    }
}
