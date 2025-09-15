package p1multiThreading;
import java.util.concurrent.Semaphore;


class taskA implements Runnable{
    private String name;
    private static final Semaphore sem = new Semaphore(3);

    public taskA(String name){
        this.name = name;
    }

    @Override
    public void run(){
        try{
            sem.acquire();
            for(int i=0; i<5; i++){
                System.out.println(name+ " : working");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted!");
        } finally {
            sem.release();
        }
    }
}

public class m5semaphore {
    public static void main(String[] args) {
        Thread t1 = new Thread(new taskA("Thread-1"));
        Thread t2 = new Thread(new taskA("Thread-2"));
        Thread t3 = new Thread(new taskA("Thread-3"));
        Thread t4 = new Thread(new taskA("Thread-4"));
        Thread t5 = new Thread(new taskA("Thread-5"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

