package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements ListLinked<E> {

    private Node<E> fstNode;
    private Node<E> lstNode;
    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        lstNode = new Node<>(null, fstNode, null);
        fstNode = new Node<>(null, null, lstNode);
    }

    @Override
    public void add(E value) {
        Node<E> pref = lstNode;
        pref.setElement(value);
        lstNode = new Node<>(null, pref, null);
        pref.setNextElement(lstNode);
        size++;
        modCount++;
        System.out.println("Добавлен элемент: " + pref.getElement());
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        Node<E> rsl = fstNode.getNextElement();

        for (int i = 0; i < index; i++) {
            rsl = getNextElement(rsl);
        }
        return rsl.getElement();
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

//    @Override
//    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            final int expectedModCount = modCount;
//            int point = 0;
//
//            @Override
//            public boolean hasNext() {
//                if (expectedModCount != modCount) {
//                    throw  new ConcurrentModificationException();
//                }
//                return point < size;
//            }
//
//            @Override
//            public E next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                return null;
//            }
//        };
//    }

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

    public static void main(String[] args) {
        ListLinked<String> str = new SimpleLinkedList<>();
        str.add("abc");
        str.add("xxx");
        str.add("ddd");
        System.out.println("Список имеет элементы с индексом 0: " + str.get(1));

    }

}
