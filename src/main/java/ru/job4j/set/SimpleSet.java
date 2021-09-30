package ru.job4j.set;

import ru.job4j.collection.list.SimpleArrayList;

import java.util.*;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : set) {
            return (Objects.equals(t, value));
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(point++);
            }
        };
    }
}
