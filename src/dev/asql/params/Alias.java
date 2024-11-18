package dev.asql.params;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alias {
    public static Map<String, String> columns = new LinkedHashMap<>();
    public static Map<String, String> selected = new LinkedHashMap<>();
    public static Map<String, String> alias = new LinkedHashMap<>();

    public static void addLine(String line, String separator) {
        String[] arr = line.split(separator);
        int i = 1;

        for (String cell : arr) {
            columns.put(cell, "c" + i);
            i++;
        }
    }

    public static String get(String column) {
        String regex = "([\\d.()+*/\\-]+)";
        Matcher matcher = Pattern.compile(regex).matcher(column);
        if (matcher.matches()) {
            return column;
        }
        regex = "\\'(.*)\\'";
        matcher = Pattern.compile(regex).matcher(column);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        regex = "(\\w+)";
        matcher = Pattern.compile(regex).matcher(column);
        if (matcher.matches()) {
            return columns.get(alias.get(column)!=null?alias.get(column):column);
        }

        regex = "(((\\w+)|[\\d.()+*/\\-])+)";
        matcher = Pattern.compile(regex).matcher(column);
        String value,v="";
        if(matcher.matches()){
            regex = "([a-zA-Z_]\\w+)";
            value=matcher.group(1);
            Matcher matcher2 = Pattern.compile(regex).matcher(column);
            while(matcher2.find()){
                v = columns.get(alias.get(matcher2.group())!=null?alias.get(matcher2.group()):matcher2.group());
                value = value.replace(matcher2.group(),v);
            }
            return value;
        }
        return null;
    }

    public static void all_alias(){
        System.out.println("------- ALL ALIAS -------");
        alias.forEach((key, value) -> System.out.println(key + " => " + value));
    }

    public static void all_selected(){
        System.out.println("------- ALL SELECTED -------");
        selected.forEach((key, value) -> System.out.println(key + " => " + value));
    }

    public static void all_columns(){
        System.out.println("------- ALL COLUMNS -------");
        columns.forEach((key, value) -> System.out.println(key + " => " + value));
    }
}
