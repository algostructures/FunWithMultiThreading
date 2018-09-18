package com.multithreading.FireAndForget.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static ExecutorService pool = Executors.newCachedThreadPool();
    public static void main(String args[]) {

        long startTimeUnPool = System.nanoTime();


        Worker worker = new Worker();
        for(int i = 0; i < 100; i++) {
            worker.printSomeNumbers(1_000_000);
        }

        long endTimeUnPool= System.nanoTime();

        long durationunpool = (endTimeUnPool - startTimeUnPool);

        long startTime = System.nanoTime();

        for(int i = 0; i < 100; i++) {
            pool.submit(()->worker.printSomeNumbers(1_000_000));
        }

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println(duration+" "+durationunpool);
    }
}
