package p1multiThreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class m4conditonalVariable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new task("t1"));
        Thread t2 = new Thread(new task("t2"));

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

class task implements Runnable{
    private static final Lock lock = new ReentrantLock();
    private static final Condition cond = lock.newCondition();
    private static int done = 1;
    private String name;

    public task(String name){
        this.name = name;
    }

    @Override
    public void run(){
        lock.lock();
        try{
            if(done == 1){
                done = 2;
                System.out.println("waiting on condition variable cond : " +name);
                cond.await();
                System.out.println("condition met : " +name);
            }
            else{
                lock.unlock();
                for(int i=0; i<5; i++){
                    System.out.println(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
                lock.lock();
                System.out.println("signaling condition variable cond : " +name);
                cond.signalAll();
                System.out.println("notifiaction done : " +name);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
