package ru.job4j.set;

import ru.job4j.collection.list.SimpleArrayList;

import java.util.*;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean rsl;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        } else {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i).equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

}
