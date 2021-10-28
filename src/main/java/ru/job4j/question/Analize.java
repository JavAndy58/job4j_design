package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        Info rslInfo = new Info(0, 0, 0);
        Map<Integer, String> rslMap = new HashMap<>();

        for(User currentUser : current) {
            rslMap.put(currentUser.getId(), currentUser.getName());
        }

//        for(User previousUser : previous) {
//
//
//
//            if (Objects.equals(rslMap.put(previousUser.getId(), previousUser.getName()), null)) {
//                rslInfo.setDeleted(1);
//            }
//            if (!Objects.equals(rslMap.put(previousUser.getId(), previousUser.getName()), previousUser.getName())) {
//                rslInfo.setChanged(rslInfo.getChanged() + 1);
//            }
//
//        }

//        rslInfo.setAdded(current.size() - previous.size() - rslInfo.getAdded());

        return new Info(rslInfo.getAdded(), rslInfo.getChanged(), rslInfo.getDeleted());
    }


}