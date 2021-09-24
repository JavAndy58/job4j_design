package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }

    public T poll() {
        T temp = null;
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException();
        }
        if (sizeOut == 0) {
            while (sizeIn != 0) {
                out.push(in.pop());
                sizeIn--;
                sizeOut++;
            }
        }
        if (sizeOut != 0) {
            temp = out.pop();
            sizeOut--;
        }
        return temp;
    }
}
