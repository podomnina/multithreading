package com.company;

public class Puttaker {
    private String field;

    private void clearValue() {
        this.field = null;
    }

    public synchronized String take() throws InterruptedException {
        while (field == null) {
            wait();
        }
        String value = field.toLowerCase();
        clearValue();
        notifyAll();
        return value;
    }

    public synchronized void put(String field) throws InterruptedException {
        while (this.field != null) {
            wait();
        }
        this.field = field;
        notifyAll();
    }
}
