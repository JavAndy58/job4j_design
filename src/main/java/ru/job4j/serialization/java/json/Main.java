package ru.job4j.serialization.java.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.collection.list.List;
import ru.job4j.collection.list.SimpleArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        JSONObject jsonCarMazda = new JSONObject("{\"modelAuto\":\"CX-5\"}");



        List<String> list = new ArrayList<>();
        list.add("Passport");
        list.add("License");
        JSONArray jsonDocumentses = new JSONArray(list);

        final Driver driver = new Driver("Petr", 40, true,
                new CarMazda("CX-5"), new String[] {"Passport", "License"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", driver.getName());
        jsonObject.put("age", driver.getAge());
        jsonObject.put("sex", driver.isSex());
        jsonObject.put("carMazda", jsonCarMazda);
        jsonObject.put("documentses", jsonDocumentses);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(driver));
    }
}
