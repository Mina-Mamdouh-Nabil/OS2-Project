package accounts_transaction;

import java.util.concurrent.Semaphore;

public class Account {

    public int number;
    public Semaphore mutex = new Semaphore(1);
    
    public Account(int number){
        this.number = number;
    }

    public void acquire() {
        while (!isFree()) {
            try {
                wait();
            } catch (Exception ex) {}
        }
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {}
    }
    
    public void release(){
        mutex.release();
    }

    public boolean isFree() {
        return mutex.availablePermits() > 0;
    }

}
