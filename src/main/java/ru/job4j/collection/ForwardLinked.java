package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        Node<T> rsl = head;
        Node<T> temp;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next != null) {
            temp = head.next;
            head.next = null;
            head = temp;
        } else {
            head = null;
        }
        return rsl.value;
    }

    public boolean revert() {
        boolean rsl;
        if (head == null || head.next == null) {
            rsl = false;
        } else {
            Node<T> pref = null;
            Node<T> current = head;
            Node<T> next = head.next;

            while (current != null) {
                next = current.next;
                current.next = pref;
                pref = current;
                current = next;
            }
            head = pref;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}