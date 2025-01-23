package ru.job4j.org;

import java.util.concurrent.atomic.AtomicInteger;

public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int value;
        do {
            value = count.get();
        } while (!count.compareAndSet(value, value + 1));

    }
    public int get() {
        return count.get();
    }

    public static void main(String[] args) {
        CASCount count = new CASCount();
       Thread thread1 = new Thread(() -> {
           for (int i = 0; i < 100_000; i++) {
               count.increment();
           }
       });
       Thread thread2 = new Thread(() -> {
           for (int i = 0; i < 100_000; i++) {
               count.increment();
           }
       });
       thread1.start();
       thread2.start();
       try {
           thread1.join();
           thread2.join();
       } catch (InterruptedException e) {
           throw new RuntimeException();
       }
        System.out.println(count.get());
    }
}
