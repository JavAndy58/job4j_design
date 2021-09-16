package ru.job4j.collection.list;

public interface ListLinked<E> extends Iterable<E> {

    void add(E value);
    E get(int index);
}
