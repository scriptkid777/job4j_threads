package ru.job4j.org.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelIndexSearchTest {

@Test
public void whenSearchThen59() {
    Integer[] array = new Integer[100];
    for (int i = 0; i < array.length; i++) {
        array[i] = i;
    }
    ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(array, 0, array.length, 59);
    assertThat(search.compute()).isEqualTo(59);
}

    @Test
    public void whenSearchThen10() {
        String string = "abcdefghijklmnopqrstuvwxyz";
        Character[] array = string.chars()
                .mapToObj(i -> (char) i)
                .toArray(Character[]::new);
        ParallelIndexSearch<Character> search = new ParallelIndexSearch<>(array, 0, array.length, 'k');
        assertThat(search.compute()).isEqualTo(10);
    }

    @Test
    public void whenLineSearchThen3() {
        Integer[] array = new Integer[] {1, 2, 4, 34, 54, 6, 7};
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(array, 0, array.length, 34);
        assertThat(search.compute()).isEqualTo(3);
    }
    @Test
    public void whenNotElementThenMinus1() {
        Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ParallelIndexSearch<Integer> search = new ParallelIndexSearch<>(array, 0, array.length, 50);
        assertThat(search.compute()).isEqualTo(-1);
    }


}