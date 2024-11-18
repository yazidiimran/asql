package dev.asql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class For {
    public static void trait(String s) throws Exception {
        String regex="for\\s+(?<init>\\$\\w+)\\s+in\\s+(?<from>\\d+)\\.\\.(?<to>\\d+)\\s+loop\\s*(?<code>.*)\\s*endfor;";
        Matcher matcher= Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            for (int i = Integer.parseInt(matcher.group("from")); i <= Integer.parseInt(matcher.group("to")); i++) {
                Set.variables.put(matcher.group("init"), String.valueOf(i));
                ASQL.execute(matcher.group("code"));
            }
            Set.variables.remove(matcher.group("init"));
        }else{
            throw new Exception("for loop not match !");
        }
    }
}
