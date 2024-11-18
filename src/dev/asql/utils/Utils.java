package dev.asql.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static boolean inList(String value, List<String> list){
        for(String v:list){
            if(v.equals(value))
                return true;
        }
        return false;
    }
    public static String mapToString(Map<?, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
