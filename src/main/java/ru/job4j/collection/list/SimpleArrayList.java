package ru.job4j.collection.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T t) {
        if (size() >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = t;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = null;
        Objects.checkIndex(index, size);
        rsl = container[index];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = null;
        Objects.checkIndex(index, size);
        rsl = container[index];
        System.arraycopy(container, index + 1, container, index, size() - index - 1);
        container[size() - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[point++];
            }
        };
    }
}