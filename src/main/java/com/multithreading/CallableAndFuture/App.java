package com.multithreading.CallableAndFuture;

;import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            Random random = new Random();

            int duration = random.nextInt(4000);

            if(duration > 2000) {
                throw new IOException("Sleeping for too long.");
            }

            Thread.sleep(duration);

            System.out.println("Finished");

            return duration;
        });

        executorService.shutdown();

        try {
            System.out.println("Result is "+ future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
