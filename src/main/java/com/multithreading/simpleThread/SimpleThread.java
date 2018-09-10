package com.multithreading.simpleThread;

public class SimpleThread {

    public static void main(String args[]){
        Runnable runnable = () -> {
            System.out.println("Running in " + Thread.currentThread().getName());
        };

        Thread t = new Thread(runnable);
        t.setName("simple-thread");
        t.start();
    }
}
