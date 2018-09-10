package com.multithreading.RaceCondition.UnResolved;

public class ValueWorker {

    private long value;

    public ValueWorker(long value) {
        this.value = value;
    }

    public long getValue(){
        return value;
    }

    public void incrementValue(){
        value += 1;
    }
}
