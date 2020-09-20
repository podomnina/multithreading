package com.company;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static final int CAPACITY = 10;
    private static final int ONE_COUNTER_NUMBER = 50000;
    private static final int MAX_COUNTER_NUMBER = 100000;

    public static void main(String[] args) throws InterruptedException {
        fourthExample();
    }

    private static void firstExample() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

        Runnable readRunnable = () -> {
            try {
                for (int i = 0; i < CAPACITY; i++) {
                    Integer num = queue.take();
                    System.out.println("Num result: " + num);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                for (int i = 0; i < CAPACITY; i++) {
                    queue.put(i);
                    System.out.println("Added new value: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread read = new Thread(readRunnable);
        Thread write = new Thread(writeRunnable);

        System.out.println("Starting process...");
        write.start();
        read.start();
        System.out.println("Done");
    }

    private static void secondExample() {
        Puttaker puttaker = new Puttaker();

        Runnable readRunnable = () -> {
            try {
                for (int i = 0; i < CAPACITY; i++) {
                    String value = puttaker.take();
                    System.out.println("Got value: " + value);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                String value = "Yamete!";
                for (int i = 0; i < CAPACITY; i++) {
                    puttaker.put(value + i);
                    System.out.println("Set value num: " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(readRunnable).start();
        new Thread(writeRunnable).start();
    }

    private static void thirdExample() throws InterruptedException {
        Counter counter = new Counter();

        Runnable firstRunnable = () -> {
            for (int i = 0; i < ONE_COUNTER_NUMBER; i++) {
                counter.increment();
            }
            System.out.println("First completed: " + counter.getValue());
        };

        Runnable secondRunnable = () -> {
            for (int i = 0; i < ONE_COUNTER_NUMBER; i++) {
                counter.increment();
            }
            System.out.println("Second completed: " + counter.getValue());
        };

        Thread first = new Thread(firstRunnable);
        first.start();
        Thread second = new Thread(secondRunnable);
        second.start();
        first.join();
        second.join();
        System.out.println("Counter: " + counter.getValue());
    }

    private static void fourthExample() throws InterruptedException {
        Counter counter = new Counter();

        Runnable firstRunnable = () -> {
            for (int i = 0; i < ONE_COUNTER_NUMBER; i++) {
                counter.increment();
            }
            System.out.println("First completed: " + counter.getValue());
        };

        Runnable secondRunnable = () -> {
            for (int i = 0; i < ONE_COUNTER_NUMBER; i++) {
                counter.increment();
            }
            System.out.println("Second completed: " + counter.getValue());
        };

        Runnable thirdRunnable = () -> {
            Date before = new Date();
            while (counter.getValue() != MAX_COUNTER_NUMBER) {
            }
            System.out.println("Counter: " + counter.getValue());
            Date after = new Date();
            long res = after.getTime() - before.getTime();
            System.out.println("Time: " + res);
        };

        Thread first = new Thread(firstRunnable);
        first.start();
        Thread second = new Thread(secondRunnable);
        second.start();
        new Thread(thirdRunnable).start();
    }

}
