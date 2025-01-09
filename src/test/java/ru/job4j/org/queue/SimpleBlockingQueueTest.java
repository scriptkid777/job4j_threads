package ru.job4j.org.queue;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
class SimpleBlockingQueueTest {

    @Test
    public void whenOfferAndPollValue3ThenSuccess() throws InterruptedException {
    final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
    final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(500);
        final List<Integer> dataList = IntStream.range(0, 500)
                .boxed().collect(Collectors.toList());
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (!queue.isEmpty() || !Thread.currentThread().isInterrupted())  {
                try {
                    buffer.add((Integer) queue.poll());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsAll(dataList);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }

}