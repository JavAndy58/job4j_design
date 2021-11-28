package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        double weight = 80.3;
        short height = 184;
        byte size = 36;
        char letter = 'p';
        long id = 115151353543435434L;
        float width = 25;
        boolean man = true;
        LOG.debug("User age : {}, weight : {}, height : {}, size : {}, letter : {}" +
                        ", id : {}, width : {}, man : {}", age, weight, height, size, letter, id, width, man);
    }
}