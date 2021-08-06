package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;


class EvenNumbersIterator implements Iterator<Integer> {
    final int[] numbers;
    private int point = 0;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
//        while (point < numbers.length && point % 2 == 0) {
//            point++;
//        }
        return point < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int rsl = numbers[point];
        while (point % 2 != 0) {
            point++;
        }
        return rsl;
    }
}
