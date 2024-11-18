package dev.asql.select;

import dev.asql.ASQL;
import dev.asql.Set;
import dev.asql.params.Alias;
import dev.asql.utils.Agregation;
import dev.asql.utils.Display;
import dev.asql.utils.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.asql.calcul.Logique.isTrue;
import static dev.asql.utils.Calcul.arithmethique;

public class Select {

    public static void trait(String s) throws Exception {
        String regex ="select\\s(.*?)\\s+from\\s+(.*?)\\s*(where (.*?)\\s*)?";
        Matcher matcher = Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            String[] columns = matcher.group(1).split(",");
            String path = matcher.group(2).startsWith("$")? Set.variables.get(matcher.group(2)):matcher.group(2);
            String conditions = matcher.group(4);
            List<List<String>> file = File.read(path, ASQL.fileSeparator,true,true);
            for (int i = 0; i < columns.length; i++) {
                regex = "(.*?)\\s+as\\s+(\\w+)";
                matcher = Pattern.compile(regex).matcher(columns[i].trim());
                if(matcher.matches()){
                    Alias.alias.put(matcher.group(2),matcher.group(1));
                    columns[i]=matcher.group(1);
                }
                Alias.selected.put(String.valueOf(i),columns[i].trim());
                //System.out.println("s = " + Alias.get(columns[i].trim()));
                columns[i] = Alias.get(columns[i].trim());
            }
            file = get(file,List.of(columns),conditions==null?"":conditions);
            Display.show(file);
        }else{
            throw new Exception("Problem in your SQL statement:");
        }

    }

    public static List<List<String>> get(List<List<String>> file, List<String> columns,String condition) throws Exception {
        String regex = "(sum|count|avg|max|min)\\(c(\\d+)\\)";
        Matcher matcher;
        for(String col:columns){
            matcher = Pattern.compile(regex).matcher(col);
            if(matcher.matches())
               throw new Exception("You have an error in your SQL, try to add group by clause !");
        }
        regex="(.*)(=|<>|<|>|<=|>=|like)(.*)";
        matcher = Pattern.compile(regex).matcher(condition);
        while (matcher.find()){
            System.out.println(condition+" "+matcher.group(1)+" "+Alias.alias.get("dbl"));
            condition=condition.replaceAll(matcher.group(1),Alias.get(matcher.group(1).trim()));
            System.out.println(condition);
        }
        return traitNormal(file,columns,condition);
    }
    public static List<List<String>> get(List<List<String>> file, List<String> columns,List<String> groupBy) throws Exception {
        return traitAgregation(file,columns,groupBy);
    }


    private static List<List<String>> traitNormal(List<List<String>> file, List<String> columns,String condition) {
        List<List<String>> resFile = new ArrayList<>();
        for (List<String> strings : file) {
            List<String> row = new ArrayList<>();
            if (checkCondition(strings, condition)) {
                for (String c : columns) {
                    if(c.equals("*")){
                        row.addAll(strings);
                    }else{
                        row.add(reg(c, strings));
                    }
                }
                resFile.add(row);
            }
        }
        return resFile;
    }

    private static boolean checkCondition(List<String> row, String condition) {
        if(condition.isEmpty()) return true;
        for (int i = 0; i < row.size(); i++) {
            System.out.println(" condition = " + condition);
            condition = condition.replaceAll("c"+(i+1),row.get(i));
        }
        return Boolean.parseBoolean(isTrue(condition));
    }

    private static List<List<String>> traitAgregation(List<List<String>> file, List<String> columns,List<String> groupBy) throws Exception {

        String regex = "(sum|count|avg|max|min)\\(c(\\d+)\\)";
        Matcher matcher = Pattern.compile(regex).matcher(columns.get(0));
        if(matcher.matches()){
            switch (matcher.group(1)){
                case "max":
                    return Agregation.max(file, Integer.parseInt(matcher.group(2)),Integer.parseInt(groupBy.get(0)));
                case "min":
                    return Agregation.min(file, Integer.parseInt(matcher.group(2)),Integer.parseInt(groupBy.get(0)));
                case "count":
                    return Agregation.count(file, Integer.parseInt(matcher.group(2)),Integer.parseInt(groupBy.get(0)));
                case "sum":
                    return Agregation.sum(file, Integer.parseInt(matcher.group(2)),Integer.parseInt(groupBy.get(0)));
                case "avg":
                    return Agregation.avg(file, Integer.parseInt(matcher.group(2)),Integer.parseInt(groupBy.get(0)));
            }
        }else{
            throw new Exception("You have an error in your Sql, You can't use group by without agregation function!");
        }
        return Collections.emptyList();
    }

    public static String reg(String column, List<String> f){
        String regex = "(\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(column);
        if(matcher.matches()){
            return column;
        }
        regex = "\\'(.*)\\'";
        matcher = Pattern.compile(regex).matcher(column);
        if(matcher.matches()){
            return matcher.group(1);
        }
        regex = "c(\\d+)";
        matcher = Pattern.compile(regex).matcher(column);
        if(matcher.matches()){
               return f.get(-1+Integer.parseInt(column.substring(1)));
        }

        regex = "([\\d.()+*/\\-]+)";
        matcher = Pattern.compile(regex).matcher(column);
        if(matcher.matches()){
            return String.valueOf(arithmethique(matcher.group(1)));
        }

        regex = "(((c\\d)|[\\d.()+*/\\-])+)";
        matcher = Pattern.compile(regex).matcher(column);
        String value,v="";
        if(matcher.matches()){
            regex = "(c\\d)";
            value=matcher.group(1);
            Matcher matcher2 = Pattern.compile(regex).matcher(column);
            while(matcher2.find()){
                v = f.get(-1+Integer.parseInt(matcher2.group().substring(1)));
                value = value.replace(matcher2.group(),v);
                //System.out.println("fnd: "+matcher2.group()+" "+value);
            }
             return String.valueOf(arithmethique(value));
        }
        return null;
    }




}
