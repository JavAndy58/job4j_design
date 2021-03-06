package ru.job4j.collection.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    @Test
    public void whenAddAndGet() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), Is.is(1));
        assertThat(list.get(1), Is.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(4);
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        list.add(null);
        Assert.assertNull(list.get(0));
        Assert.assertNull(list.get(1));
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(1));
        assertThat(first.hasNext(), Is.is(true));
        assertThat(first.next(), Is.is(2));
        assertThat(first.hasNext(), Is.is(false));

        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(1));
        assertThat(second.hasNext(), Is.is(true));
        assertThat(second.next(), Is.is(2));
        assertThat(second.hasNext(), Is.is(false));
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        ListLinked<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        list.add(0);
        iterator.next();
    }
}