package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String stringData = getStringDataFromFile(file);
        Map<String, String> mapData = getValuesFromStringData(stringData);

        Profile profile = new Profile();
        profile.setName(mapData.get("Name"));
        profile.setAge(Integer.valueOf(mapData.get("Age")));
        profile.setEmail(mapData.get("Email"));
        profile.setPhone(Long.valueOf(mapData.get("Phone")));

        return profile;
    }

    private String getStringDataFromFile(File file) {
        String result = "";
        try {
            result = Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Map<String, String> getValuesFromStringData(String data) {
        Map<String, String> result = new HashMap<>();
        List<String> listData = List.of(data.split("\n"));
        listData.forEach(item -> {
            String key = item.split(":")[0];
            String val = item.split(": ")[1].strip();
            result.put(key, val);
        });

        return result;
    }
}
