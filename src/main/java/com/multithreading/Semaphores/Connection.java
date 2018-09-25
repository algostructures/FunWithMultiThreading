package com.multithreading.Semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10, true);
    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {

            // get permit decrease the sem value, if 0 wait for release
            sem.acquire();

            //if doConnect throws and exception is still releases the permit
            //so we use a separate method here to increase the connections count
            doConnect();

        } catch (InterruptedException ignored) {
        } finally {
            //release permit, increase the sem value and activate waiting thread
            sem.release();
        }
    }

    public void doConnect() {
        synchronized (this) { //atomic
            connections++;
            System.out.println("Current connections (max 10 allowed): " + connections);
        }
        try {
            //do your job
            System.out.println("Working on connections " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        //when exit doConnect method decrement number of connections
        synchronized (this) {//atomic
            connections--;
            System.out.println("I'm done " + Thread.currentThread().getName() + " Connection is released , connection count: " + connections);
        }
    }
}
