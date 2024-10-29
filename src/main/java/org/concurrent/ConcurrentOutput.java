package org.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        Thread.sleep(500);
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
