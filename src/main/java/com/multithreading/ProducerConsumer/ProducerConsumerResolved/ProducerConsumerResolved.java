package com.multithreading.ProducerConsumer.ProducerConsumerResolved;

import com.multithreading.ProducerConsumer.SynchronizedDeadlock.ProducerConsumerDeadlock;

public class ProducerConsumerResolved {
    private static final Object lock = new Object();
    private static int[] buffer;
    private static int count;

    private static class Producer {

        private void produce() throws InterruptedException {
            synchronized (lock) {
                if (isFull(buffer)) {
                    lock.wait();
                }
                buffer[count++] = 1;
                lock.notify();
            }
        }

    }

    static class Consumer {

        void consume() throws InterruptedException {
            synchronized (lock) {
                if (isEmpty(buffer)) {
                    lock.wait();
                }
                buffer[--count] = 0;
                lock.notify();
            }

        }
    }

    private static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    private static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    public static void main(String args[]) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        ProducerConsumerResolved.Producer producer = new ProducerConsumerResolved.Producer();
        ProducerConsumerResolved.Consumer consumer = new ProducerConsumerResolved.Consumer();

        Runnable produceTask = () -> {
            for(int i = 0; i < 50; i++) {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Done Producing");
        };

        Runnable consumeTask = () -> {
            for(int i = 0; i < 50; i++) {
                try {
                    consumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Done Consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread producerThread = new Thread(produceTask);

        consumerThread.start();
        producerThread.start();

        consumerThread.join();
        producerThread.join();

        System.out.println("Data in the Buffer " + count);
    }
}
