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
}
