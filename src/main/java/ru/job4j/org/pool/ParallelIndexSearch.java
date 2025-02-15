package ru.job4j.org.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final int limit = 10;

    private final T[] array;

    private final int start;

    private final int end;

    private final T searchElement;


    public ParallelIndexSearch(T[] array, int start, int end, T searchElement) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }


    private int lineSearch(int start, int end) {
        for (int index = start; index < end; index++) {
            if (searchElement.equals(array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (end - start <= limit) {
            return lineSearch(start, end);
        }
      int middle = (start + end) / 2;
        ParallelIndexSearch<T> left = new ParallelIndexSearch<>(array, start, middle, searchElement);
        ParallelIndexSearch<T> right = new ParallelIndexSearch<>(array, middle + 1, end, searchElement);
        left.fork();
        right.fork();
        Integer leftJ = left.join();
        Integer rightJ = right.join();
        return leftJ != -1 ? leftJ : rightJ;
    }
}
