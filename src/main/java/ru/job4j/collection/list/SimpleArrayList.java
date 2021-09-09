package ru.job4j.collection.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;
    private int point = 0;

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
        int indexTemp = Objects.checkIndex(index, size);
        rsl = container[indexTemp];
        container[indexTemp] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = null;
        int indexTemp = Objects.checkIndex(index, size);
        rsl = container[indexTemp];
        System.arraycopy(container, indexTemp + 1, container, indexTemp, size() - indexTemp - 1);
        container[size() - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

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