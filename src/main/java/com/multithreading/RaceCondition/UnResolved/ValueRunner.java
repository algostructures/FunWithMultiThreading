package com.multithreading.RaceCondition.UnResolved;

import java.util.stream.IntStream;

public class ValueRunner {
    /*
    * When we run this program value should be come as 1_000_000
    * but due to race condition it won't
    * */
    public static void main (String args[]) throws InterruptedException {
        ValueWorker valueWorker = new ValueWorker(0L);

        Runnable r = () -> {
            IntStream.range(0, 1_000).forEach(i -> valueWorker.incrementValue());
        };

        Thread[] threads = new Thread[1_000];

        IntStream.range(0, 1_000).forEach(i -> {
            threads[i] = new Thread(r);
            threads[i].start();
        });

        for(int i = 0; i < 1_000; i++) threads[i].join();

        System.out.println("Value = "+ valueWorker.getValue());
    }

}
