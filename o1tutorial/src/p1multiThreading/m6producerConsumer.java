package p1multiThreading;

import java.util.LinkedList;
import java.util.Queue;

public class m6producerConsumer {

    static class Buffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity = 10;

        public synchronized void produce(int item) throws InterruptedException{
            while(queue.size() == capacity){
                System.out.println("Buffer is full. producer is waiting... ");
                wait();
            }
            queue.add(item);
            System.out.println("produced..." +item);
            notify();
        }

        public synchronized int consume() throws InterruptedException{
            while(queue.isEmpty()){
                System.out.println("Buffer is empty. consumer is waiting... ");
                wait();
            }
            int item = queue.remove();
            System.out.println("consumed..." +item);
            notify();
            return item;
        }
    }

    static class Producer extends Thread{
        private Buffer buffer;
        public Producer(Buffer buffer){
            this.buffer = buffer;
        }
        public void run(){
            int item = 0;
            try{
                while(true){
                    buffer.produce(item++);
                    Thread.sleep(500);
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread{
        private Buffer buffer;
        public Consumer(Buffer buffer){
            this.buffer = buffer;
        }
        public void run(){
            try{
                while(true){
                    buffer.consume();
                    Thread.sleep(1000);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();
    }
}

