package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static final int CAPACITY = 10;

    public static void main(String[] args) {
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
}
