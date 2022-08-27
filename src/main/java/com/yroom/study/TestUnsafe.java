package com.yroom.study;

/**
 * @author YROOM
 * @date 2022/5/3 11:41
 */
public class TestUnsafe {
    public static double money = 100;

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.setAccount_money(100);


        Thread t1 = new Thread(() -> {

            money = money-100;
            System.out.println("t1 start 取钱:" + String.valueOf(money) + "时间" + System.currentTimeMillis());
        });

        Thread t2 = new Thread(() -> {
            money = money-100;
            System.out.println("t2 start 取钱:" + String.valueOf(money) + "时间" + System.currentTimeMillis());
        });

        t1.start();
        t2.start();

        System.out.println("main end:最后钱数" + money + "时间" + System.currentTimeMillis());


    }
}
