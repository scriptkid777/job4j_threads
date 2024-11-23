package ru.job4j.org.concurrent;

public  final class Cache {
    private  static Cache cache;

    public synchronized static Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }

        return cache;
    }
}
