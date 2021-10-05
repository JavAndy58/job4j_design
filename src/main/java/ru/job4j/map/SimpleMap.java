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
        int index = indexTable(key);

        if (table[index] != null) {
            return false;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int indexTable(K key) {
        int rslHashCode = Objects.hashCode(key);
        int rslHash = hash(rslHashCode);
        return indexFor(rslHash);
    }

    private void expand() {
        if (capacity == 0) {
            MapEntry<K, V>[] table = new MapEntry[capacity];
            return;
        }
        capacity *= 2;
        MapEntry<K, V>[] tableTemp = new MapEntry[capacity];
        for (MapEntry<K, V> objTable : table) {
            if (objTable == null) {
                continue;
            }
            K key = objTable.getKey();
            int index = indexTable(key);

            tableTemp[index] = objTable;
        }
        table = tableTemp;
    }

    @Override
    public V get(K key) {
        int index = indexTable(key);

        if (table[index] == null) {
            return null;
        }
        if (table[index].getKey() == key) {
            return table[index].getValue();
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexTable(key);

        if (table[index] == null) {
            return false;
        }
        if (table[index].getKey() == key) {
            table[index] = null;
            count--;
            modCount--;
        }
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

