package ru.job4j.org;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
class CASCountTest {
    @Test
    void when2Threads50Then100() throws InterruptedException {
        CASCount count = new CASCount();
        Thread firstThread = new Thread(() -> IntStream.range(0, 50).forEach(i -> count.increment()));
        Thread secondThread = new Thread(() -> IntStream.range(0, 50).forEach(i -> count.increment()));
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        assertThat(count.get()).isEqualTo(100);
    }

    @Test
    public void whenIncrementThenSuccessfully() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread thread1 = new Thread(casCount::increment);
        Thread thread2 = new Thread(casCount::increment);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get()).isEqualTo(2);
    }

}