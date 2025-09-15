package p1multiThreading;

class TaskA implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("taskA : %d\n", i);
        }
    }
}

class TaskB implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("taskB : %d\n", i);
        }
    }
}

public class m1multiThreading {
    public static void main(String[] args) {
        Thread t1 = new Thread(new TaskA());
        Thread t2 = new Thread(new TaskB());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


