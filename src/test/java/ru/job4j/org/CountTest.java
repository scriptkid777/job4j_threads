package ru.job4j.org;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountTest {
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        var count = new Count();
        var first = new Thread(count::increment);
        var second = new Thread(count::increment);
        first.start();
        second.start();
        /* Заставляем главную нить дождаться выполнения наших нитей. */
        first.join();
        second.join();
        /* Проверяем результат. */
        assertThat(count.get()).isEqualTo(2);
    }
}