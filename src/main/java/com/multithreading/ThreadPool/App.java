package com.multithreading.ThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: "+ id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: "+ id);
    }
}

public class App {

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0,5).forEach(i-> executorService.submit(new Processor(i)));


        executorService.shutdown();

        System.out.println("All Tasks Submitted.");

        try{
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (Exception e) {

        }
        System.out.println("All Tasks Completed");
    }
}
