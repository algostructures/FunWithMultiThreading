package com.multithreading.ProducerConsumer.raceCondition;

public class ProducerConsumerRaceCondition {

    private static int[] buffer;
    private static int count;

    static class Producer {

        private void produce() {
            while (isFull(buffer)) {}
            buffer[count++] = 1;
        }

    }

    static class Consumer {

        void consume() {
            while(isEmpty(buffer)) {}
                buffer[--count] = 0;

        }
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    public static void main(String args[]) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for(int i = 0; i < 50; i++) {
                producer.produce();
            }
            System.out.println("Done Producing");
        };

        Runnable consumeTask = () -> {
            for(int i = 0; i < 50; i++) {
                consumer.consume();
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
