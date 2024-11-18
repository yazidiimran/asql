package dev.asql.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Array {
    public static boolean isArray(String s) {
        Matcher matcher = Pattern.compile("").matcher(s);
        if(matcher.matches())
            return true;
        return false;
    }

    public static String[] toArray(String s) {

        Matcher matcher = Pattern.compile("\\[.(,.*)*\\]").matcher(s);
        if(matcher.matches()){
            s = s.replace("[","");
            s = s.replace("]","");
            return s.split(",");
        }
        return null;
    }


}
