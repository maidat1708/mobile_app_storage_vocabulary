package com.example.btlmobile.model;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ContriesCode {
    private Map countryCodeMap = new LinkedHashMap();

    public ContriesCode() {
        countryCodeMap.put("Anh", "en");
        countryCodeMap.put("Việt Nam", "vi");
        countryCodeMap.put("Trung Quốc", "zh-CN");
        countryCodeMap.put("Nhật Bản", "ja");
        countryCodeMap.put("Hàn Quốc", "ko");
    }

    public Map getCountryCodeMap() {
        return countryCodeMap;
    }

    public void setCountryCodeMap(Map countryCodeMap) {
        this.countryCodeMap = countryCodeMap;
    }
    public static String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
