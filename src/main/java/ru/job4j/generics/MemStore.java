package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rts = false;
        if (mem.containsKey(id)) {
            rts = true;
            mem.put(id, model);
        }
        return rts;
    }

    @Override
    public boolean delete(String id) {
        boolean rts = false;
        if (mem.containsKey(id)) {
            rts = true;
            mem.remove(id);
        }
        return rts;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
