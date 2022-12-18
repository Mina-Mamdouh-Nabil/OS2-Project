package accounts_transaction;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction extends Thread {

    public boolean done = false;
    public int number;
    public Account firstAccount;
    public Account secondAccount;
    Semaphore sem = new Semaphore(1);
    public static int count = 0;

    public Transaction(int number, Account firstAccount, Account secondAccount) {
        this.number = number;
        this.firstAccount = firstAccount;
        this.secondAccount = secondAccount;
    }

    @Override
    public void run() {
        while (!done) {
            System.out.println("Transaction " + number + " is waiting");
            if (number %2== 0) {
                firstAccount.acquire();
                System.out.println("Transaction " + number + " acquire the first account " + firstAccount.number);
                secondAccount.acquire();
                System.out.println("Transaction " + number + " acquire the second account " + secondAccount.number);
            } else {
                secondAccount.acquire();
                System.out.println("Transaction " + number + " acquire the second account " + secondAccount.number);
                firstAccount.acquire();
                System.out.println("Transaction " + number + " acquire the first account " + firstAccount.number);
            }
            System.out.println("Transaction " + number + " is running...");
            firstAccount.release();
            System.out.println("Transaction " + number + " release the first account " + firstAccount.number);
            secondAccount.release();
            System.out.println("Transaction " + number + " release the second account " + secondAccount.number);
            done = true;
            try {
                sem.acquire();
                this.count++;
//                System.out.println("Count : "+count);
                sem.release();
            } catch (InterruptedException ex) {
            }
        }
    }

}
