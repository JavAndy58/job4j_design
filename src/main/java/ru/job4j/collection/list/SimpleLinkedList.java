package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements ListLinked<E> {
    private Node<E> head;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            modCount++;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        Node<E> rsl = head;
        for (int i = 0; i < index; i++) {
            rsl = rsl.getNext();
        }
        return rsl.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> rsl = head;
                for (int i = 0; i < point; i++) {
                    rsl = rsl.getNext();
                }
                point++;
                return rsl.getValue();
            }
        };
    }

    private class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }

        public E getValue() {
            return value;
        }
    }
}
