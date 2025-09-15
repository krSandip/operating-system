package p1multiThreading;

public class m2raceCondition {
    public static void main(String[] args) {
        sharedCounter counter = new sharedCounter();

        Thread t1 = new threadCounter(counter);
        Thread t2 = new threadCounter(counter);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("final counter value : " +counter.getCount());
    }
}

class sharedCounter{
    private int count = 0;

    public void increament(){
        count++;
    }
    public int getCount(){
        return count;
    }
}

class threadCounter extends Thread{
    private sharedCounter counter;

    public threadCounter(sharedCounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i=0; i< 1000; i++){
            counter.increament();
        }
    }
}
