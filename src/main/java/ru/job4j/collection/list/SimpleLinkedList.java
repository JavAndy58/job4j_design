package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements ListLinked<E> {

    private Node<E> fstNode;
    private Node<E> lstNode;
    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        fstNode = new Node<>(null, null, lstNode);
        lstNode = new Node<>(null, fstNode, null);
    }

    @Override
    public void add(E value) {
        Node<E> pref = lstNode;
        pref.setElement(value);
        lstNode = new Node<>(null, pref, null);
        pref.setNextElement(lstNode);
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> rsl = fstNode;
        for(int i = 1; i < index; i++) {
            rsl = fstNode.getNextElement();
        }

        return rsl.getElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
                return null;
            }
        };
    }

    private class Node<E> {
        private E element;
        private Node<E> nextElement;
        private Node<E> prevElement;

        public Node(E element, Node<E> prevElement, Node<E> nextElement) {
            this.element = element;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public E getElement() {
            return element;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getNextElement() {
            return nextElement;
        }
        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }
        public Node<E> getPrevElement() {
            return prevElement;
        }
        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }

}
