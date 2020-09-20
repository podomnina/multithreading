package com.company;

public class Counter {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public synchronized void increment() {
        this.setValue(this.getValue() + 1);
    }
}
