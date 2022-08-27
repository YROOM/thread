package com.yroom.study;

import java.util.StringJoiner;

/**
 * @author YROOM
 * @date 2022/5/2 11:32
 */
public class SmallTool {
    public static void sleepMills(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTimeAndThread(String tag) {
        String result = new StringJoiner("\t|\t").add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(tag)
                .toString();
        System.out.println(result);
    }
}
