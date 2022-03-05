package com.fleet.excel.util.value;

import java.util.HashMap;
import java.util.Map;

public class Values {

    public Map<String, String> map = new HashMap<>();

    public String get(String key) {
        return map.get(key);
    }

    public String getKey(String value) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }
}
