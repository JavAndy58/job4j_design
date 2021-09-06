package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T t) {
        if (size() < container.length) {
            for (int index = 0; index < container.length; index++) {
                if (container[index] == null) {
                    container[index] = t;
                    modCount++;
                    break;
                }
            }
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
            modCount--;
        }
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, container.length - 1)];
    }

    @Override
    public int size() {
//        size = 0;
//        for (int index = 0; index <= container.length; index++) {
//            if (container[index] == null) {
//                break;
//            }
//            size++;
//        }
//        return size;
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                 return null;
            }

        };
    }

}