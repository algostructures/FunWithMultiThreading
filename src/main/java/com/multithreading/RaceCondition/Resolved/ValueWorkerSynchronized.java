package com.multithreading.RaceCondition.Resolved;

public class ValueWorkerSynchronized {

    private final Object key = new Object();
    private long value;

    public ValueWorkerSynchronized(long value) {
        this.value = value;
    }

    public long getValue(){
        return value;
    }

    public void incrementValue(){
        synchronized (key) {
            value += 1;
        }
    }
}
