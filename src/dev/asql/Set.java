package dev.asql;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Set {
    public static Map<String,String> variables = new LinkedHashMap<>();
    public static void trait(String s) throws Exception {
        //System.out.println("s = " + s);
        Matcher matcher=Pattern.compile("\\s*set\\s+(?<vname>\\$\\w+)\\s*=\\s*(?<value>.*)\\s*;").matcher(s);
        if(matcher.matches()) {
            if(matcher.group("value").equals("readline()")){
                Scanner input = new Scanner(System.in);
                variables.put(matcher.group("vname"), input.nextLine());
            }else{
                variables.put(matcher.group("vname"), matcher.group("value"));
            }
        }else{
            throw new Exception("set syntax error !");
        }
    }
}
