package com.yroom.tongbu;

/**
 * @author YROOM
 * @date 2022/5/3 11:18
 */
public class UnsynchBankTest {

    public static final int NACCOUNTS = 100;
    public static final double INITITAL_BALANCE = 1000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITITAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            TransferRunnable r = new TransferRunnable(b, i,INITITAL_BALANCE);
            Thread t = new Thread(r);
            t.start();
        }
    }
}
