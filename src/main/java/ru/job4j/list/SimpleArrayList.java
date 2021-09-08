package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;
    private int point = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void addArray(T t) {
        for (int index = 0; index < container.length; index++) {
            if (container[index] == null) {
                container[index] = t;
                size++;
                modCount++;
                break;
            }
        }
    }

    @Override
    public void add(T t) {
        if (size() < container.length) {
             addArray(t);
        } else if (size() >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
            addArray(t);
        }
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = null;
        if (Objects.checkIndex(index, container.length - 1) >= 0) {
            rsl = container[index];
            container[index] = newValue;
        }
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = null;
        if (Objects.checkIndex(index, container.length - 1) >= 0) {
            rsl = container[index];
            System.arraycopy(container, index + 1, container, index, size() - index - 1);
            container[size() - 1] = null;
            size--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, container.length - 1)];
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
                return point < container.length && modCount > 0;

            }

            @Override
            public T next() {

                if (size() <= 0) {
                    throw new NoSuchElementException();
                }

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[point++];
            }

        };
    }

}