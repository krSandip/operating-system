package p1multiThreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class m3locks {
    public static void main(String[] args) {
        sharedResource counter = new sharedResource();
        Thread t1 = new counterThread(counter);
        Thread t2 = new counterThread(counter);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("final counter value : " +counter.getCount());
    }
}

class sharedResource {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increament(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }

    public int getCount(){
        return count;
    }
}

class counterThread extends Thread{
    private sharedResource counter;

    public counterThread(sharedResource counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for (int i=0; i<1000; i++){
            counter.increament();
        }
    }
}
