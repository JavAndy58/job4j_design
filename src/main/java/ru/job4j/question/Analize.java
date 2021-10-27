package ru.job4j.question;

import java.util.Objects;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rslInfo = new Info(0, 0, 0);
        Map<Integer, String> rslMap = new HashMap<>();

        for (User currentUser : current) {
            rslMap.put(currentUser.getId(), currentUser.getName());
        }
        for (User previousUser : previous) {
            if (rslMap.put(previousUser.getId(), previousUser.getName()) == null) {
                rslInfo.setDeleted(rslInfo.getDeleted() + 1);
            }

            if (Objects.equals(rslMap.put(previousUser.getId(), previousUser.getName()), previousUser.getName())) {
                rslInfo.setChanged(rslInfo.getChanged() + 1);
            }
            rslMap.put(previousUser.getId(), previousUser.getName());
        }
        rslInfo.setAdded(current.size() - previous.size() - rslInfo.getDeleted());
        return new Info(rslInfo.getAdded(), rslInfo.getChanged(), rslInfo.getDeleted());
    }
}
