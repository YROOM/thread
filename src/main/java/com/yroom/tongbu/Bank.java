package com.yroom.tongbu;

/**
 * @author YROOM
 * @date 2022/5/3 11:09
 */
public class Bank {

    private final double[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < n; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.println("amount" + amount + " from " + from + " to " + to);
        accounts[to] += amount;
        System.out.println("Total balance:" + getTotalBalance());
    }

    private double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }

}
