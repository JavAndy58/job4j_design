package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutTrue() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        assertTrue(list.put(1, 10));
        assertTrue(list.put(3, 1000));
    }

    @Test
    public void whenPutFalse() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        assertTrue(list.put(0, 10));
        assertTrue(list.put(1, 100));
        assertFalse(list.put(0, 5));
        assertFalse(list.put(1, 5));
    }

    @Test
    public void whenExpandOn() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        assertTrue(list.put(0, 1));
        assertTrue(list.put(1, 10));
        assertTrue(list.put(2, 1000));
        assertTrue(list.put(3, 10));
        assertTrue(list.put(4, 1000));
        assertTrue(list.put(5, 10));
        assertTrue(list.put(6, 1000));
        assertTrue(list.put(7, 10));
        assertTrue(list.put(8, 1000));
    }

    @Test
    public void whenGetTrue() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        list.put(0, 10);
        list.put(1, 100);
        assertThat(list.get(0), Is.is(10));
        assertThat(list.get(1), Is.is(100));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        list.put(0, 10);
        Assert.assertNull(list.get(1));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        list.put(0, 10);
        list.put(1, 100);
        assertTrue(list.remove(0));
        assertTrue(list.remove(1));
    }

    @Test
    public void whenRemoveFalse() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        assertFalse(list.remove(0));
    }

    @Test
    public void whenGetIterator() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        list.put(0, 100);
        list.put(1, 1000);

        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(0));
        assertThat(iterator.hasNext(), Is.is(true));
        assertThat(iterator.next(), Is.is(1));
        assertThat(iterator.hasNext(), Is.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<Integer, Integer> list = new SimpleMap<>();
        Iterator<Integer> iterator = list.iterator();
        list.put(0, 10);
        iterator.next();
    }
}
