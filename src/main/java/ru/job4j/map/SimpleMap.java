package ru.job4j.map;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];



    @Override
    public boolean put(K key, V value) {

        if (count / table.length >= LOAD_FACTOR) {
            expand();
        }
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int indexFor = indexFor(hash);
        if (table[indexFor] == null) {
            table[indexFor] = new MapEntry<>(key, value);
            count++;
            return true;
        }
        return false;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> table.length);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
//        table = System.arraycopy(table, 0, table, 0, table.length);

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

