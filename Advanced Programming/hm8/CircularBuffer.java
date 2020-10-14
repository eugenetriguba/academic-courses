// Eugene Triguba <ytriguba17@ole.augie.edu>
// Advanced Programming: 39-3 More on Thread Synchronization
// Homework 8

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Producer implements Runnable {
    private final Buffer buf;

    public Producer(Buffer b) {
        buf = b;
    }

    public void run() {
        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(500);
                buf.set(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private final Buffer buf;

    public Consumer(Buffer b) {
        buf = b;
    }

    public void run() {
        for (int count = 1; count <= 10; count++) {
            try {

                Thread.sleep(2000);
                buf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

interface Buffer {
    public void set(int value) throws InterruptedException;

    public int get() throws InterruptedException;
}

public class CircularBuffer implements Buffer {
    private final int[] buf = { -1, -1, -1, -1, -1, -1, -1, -1 };
    private int occupiedCells = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void set(int value) throws InterruptedException {
        while (occupiedCells == buf.length) {
            System.out.printf("Buffer full Producer waits\n");
            wait();
        }
        buf[in] = value;
        System.out.printf("Producer writes\t%2d to \tbuf[%d]\n", value, in);
        in = (in + 1) % buf.length;
        ++occupiedCells;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (occupiedCells == 0) {
            System.out.printf("Buffer empty Consumer waits\n");
            wait();
        }
        int value = buf[out];
        System.out.printf("Consumer reads\t%2d from buf[%d]\n", value, out);
        out = (out + 1) % buf.length;
        --occupiedCells;
        notifyAll();
        return value;
    }

    public static void main(String[] args) {
        ExecutorService application = Executors.newCachedThreadPool();
        Buffer buf = new CircularBuffer();
        application.execute(new Producer(buf));
        application.execute(new Consumer(buf));
        application.shutdown();
    }
}
