package ru.job4j.org.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(5000); /* симулируем выполнение параллельной
        задачи в течение 5 секунд. */
            progress.interrupt();
        } catch (InterruptedException e) {
         e.printStackTrace();
        }
    }

    @Override
    public void run() {
        var process = new char[]{'-', '\\', '|', '/', '-' };
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (char symbol : process) {
                    System.out.print("\rLoad: " + symbol);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}