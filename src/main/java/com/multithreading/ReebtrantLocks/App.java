package com.multithreading.ReebtrantLocks;

public class App {

    public static void main(String[] args) throws Exception {
        final Runner runner = new Runner();
        Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException ignored) {
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException ignored) {
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        runner.finished();
    }

}
