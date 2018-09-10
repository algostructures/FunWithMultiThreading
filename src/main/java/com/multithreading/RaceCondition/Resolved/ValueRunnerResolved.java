package com.multithreading.RaceCondition.Resolved;

import java.util.stream.IntStream;

public class ValueRunnerResolved {
    /*
    * When we run this program value should be come as 1_000_000
    * Since We made  worker synchronized it will come as  1_000_000
    * */
    public static void main (String args[]) throws InterruptedException {
        ValueWorkerSynchronized valueWorkerSynchronized = new ValueWorkerSynchronized(0L);

        Runnable r = () -> {
            IntStream.range(0, 1_000).forEach(i -> valueWorkerSynchronized.incrementValue());
        };

        Thread[] threads = new Thread[1_000];

        IntStream.range(0, 1_000).forEach(i -> {
            threads[i] = new Thread(r);
            threads[i].start();
        });

        for(int i = 0; i < 1_000; i++) threads[i].join();

        System.out.println("Value = "+ valueWorkerSynchronized.getValue());
    }

}
