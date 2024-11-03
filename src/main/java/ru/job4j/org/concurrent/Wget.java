package ru.job4j.org.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        first.start();
        try {
            for (int index = 0; index <= 100; index++) {
                Thread.sleep(200);
                System.out.print("\rLoading : " + index + "%");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n Loaded.Finish");
    }
}
