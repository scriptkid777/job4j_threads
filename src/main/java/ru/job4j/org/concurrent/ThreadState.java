package ru.job4j.org.concurrent;
import static java.lang.Thread.State.TERMINATED;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getState() + " first");
        System.out.println(second.getState() + " second");
        first.start();
        second.start();
        while (first.getState() != TERMINATED || second.getState() != TERMINATED) {
            System.out.println(first.getState() + " first");
            System.out.println(second.getState() + " second");
        }
        System.out.println("Нити остановлены");
    }
}
