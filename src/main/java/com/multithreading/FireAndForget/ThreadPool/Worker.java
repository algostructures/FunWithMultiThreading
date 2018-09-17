package com.multithreading.FireAndForget.ThreadPool;

import java.util.stream.IntStream;

public class Worker {
    public void printSomeNumbers(int n) {
        IntStream.range(0, n).forEach(i-> doNothing(i));
        System.out.println("done");
    }

    private void doNothing(int i) {
    }
}
