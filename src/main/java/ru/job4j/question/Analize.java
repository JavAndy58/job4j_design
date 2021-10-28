package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        Info rslInfo = new Info(0, 0, 0);
        Map<Integer, User> rslMap = new HashMap<>();

        for (User currentUser : current) {
            rslMap.put(currentUser.getId(), currentUser);
        }
        for (User previousUser : previous) {
            if (rslMap.containsKey(previousUser.getId()) && !rslMap.containsValue(previousUser)) {
                rslInfo.setChanged(rslInfo.getChanged() + 1);
            }
            if (!rslMap.containsKey(previousUser.getId())) {
                rslInfo.setDeleted(rslInfo.getDeleted() + 1);
            }
        }
        rslInfo.setAdded(current.size() - previous.size() + rslInfo.getDeleted());
        return new Info(rslInfo.getAdded(), rslInfo.getChanged(), rslInfo.getDeleted());
    }


}