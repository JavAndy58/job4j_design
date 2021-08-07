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
        boolean rsl = false;
        for (int index = point; index < numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                point = index;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}