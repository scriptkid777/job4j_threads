package ru.job4j.org.pool.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private void emailTo(User user) {
        String subject = String.format("Notification %s to email %s.", user.name(), user.email());
        String body = String.format("Add a new event to %s", user.name());
        pool.submit(() -> send(subject, body, user.email()));
    }

    private void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }

}
