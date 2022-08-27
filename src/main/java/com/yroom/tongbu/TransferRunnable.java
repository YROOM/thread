package com.yroom.tongbu;

/**
 * @author YROOM
 * @date 2022/5/3 11:20
 */
public class TransferRunnable implements Runnable {


    private int fromAccount;
    private int toAccount;
    private double maxAmount;
    private int DELAY=10;
    private Bank bank;

    public TransferRunnable(int fromAccount, int toAccount, double maxAmount, Bank bank) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.maxAmount = maxAmount;
        this.bank = bank;
    }




    public TransferRunnable(Bank b,int from,double max){
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }


    @Override
    public void run() {
        while (true) {
            try {
                int toAccount=(int)(bank.size()*Math.random());
                double amount = (int)(maxAmount*Math.random());
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int) (Math.random() * DELAY));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
