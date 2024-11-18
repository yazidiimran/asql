package dev.asql;

import dev.asql.utils.Array;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Foreach {
    public static void trait(String s) throws Exception {
        String regex="foreach\\s+(?<element>\\$\\w+)\\s+in\\s+(?<array>\\$\\w+)\\s+loop\\s*(?<code>.*)\\s*endforeach;";
        Matcher matcher= Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            String sarray=Set.variables.get(matcher.group("array"));
            if(sarray != null){
                for (String e: Array.toArray(sarray)) {
                    Set.variables.put(matcher.group("element"), e);
                    ASQL.execute(matcher.group("code"));
                }
                Set.variables.remove(matcher.group("element"));
            }else{
                throw new Exception("Array "+matcher.group("array")+" not found !");
            }

        }else{
            throw new Exception("foreach loop not match !");
        }
    }
}
