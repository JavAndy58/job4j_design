package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
       in.push(value);
    }

    public T poll() {
        while (in.pop() != null) {
            out.push(in.pop());
        }

        T temp = null;
        if (out.pop() != null) {
            temp = out.pop();
        }
        while (out.pop() != null) {
            out.pop();
        }
        return temp;
    }
}
