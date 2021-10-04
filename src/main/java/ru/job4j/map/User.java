package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private String name;
    private String children;
    private String birthday;

    public User(String name, String children, String birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Maxim", "group1", "10.10.2000");
        User user2 = new User("Maxim", "group1", "10.10.2000");
        Map<User, Object> users = new HashMap<>();
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users);
        System.out.println("Хэш1: " + user1.hashCode());
        System.out.println("Хэш2: " + user2.hashCode());
    }
}
