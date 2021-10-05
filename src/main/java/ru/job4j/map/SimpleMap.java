package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int indexFor = indexFor(hash);

        if (count >= table.length * LOAD_FACTOR) {
            expand();
        }
        if (table[indexFor] == null) {
            table[indexFor] = new MapEntry<>(key, value);
            count++;
            modCount++;
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
        K key;

        if (table.length == 0) {
            MapEntry<K, V>[] table = new MapEntry[capacity];
        }
        MapEntry<K, V>[] tableTemp = new MapEntry[capacity * 2];
        for (MapEntry<K, V> objTable : table) {
            if (objTable == null) {
                break;
            }
            key = objTable.getKey();
            int hashCode = Objects.hashCode(key);
            int hash = hash(hashCode);
            int indexFor = indexFor(hash);

            tableTemp[indexFor] = objTable;
        }
        table = tableTemp;
    }

    @Override
    public V get(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int indexFor = indexFor(hash);

        if (table[indexFor] == null) {
            return null;
        }
        return table[indexFor].getValue();
    }

    @Override
    public boolean remove(K key) {
        int hashCode = Objects.hashCode(key);
        int hash = hash(hashCode);
        int indexFor = indexFor(hash);

        if (table[indexFor] == null) {
            return false;
        }
        table[indexFor] = null;
        count--;
        modCount--;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return point < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].getKey();
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

