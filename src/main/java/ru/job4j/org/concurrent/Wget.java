package ru.job4j.org.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> { }
        );
        first.start();
for (int index = 0; index <= 100; index++) {
    Thread.sleep(200);
    System.out.print("\rLoading : " + index + "%");
    Thread.sleep(7);
}
System.out.println("\n Loaded.Finish");
    }
}
