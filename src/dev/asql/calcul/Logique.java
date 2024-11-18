package dev.asql.calcul;

import dev.asql.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logique {

    public static String isTrue(String expression){

        String regex = "(?<op1>\\$\\w+|\\d+|c\\d+|\\'.*?\\')(like|=|>|<|<>|<=|>=)(?<op2>\\$\\w+|\\d+|c\\d+|\\'.*?\\')";
        Matcher matcher = Pattern.compile(regex).matcher(expression);
        while(matcher.find()){
            String op1 = matcher.group("op1");
            String op2 = matcher.group("op2");
            if(op1.trim().startsWith("$")){
                op1 = Set.variables.get(op1);
            }
            if(op2.trim().startsWith("$")){
                op2 = Set.variables.get(op2);
            }
            expression = expression.replace(matcher.group(),eval(op1,matcher.group(2),op2));
        }

        regex = "(true|false)\\s+(and|or)\\s+(true|false)";
        matcher = Pattern.compile(regex).matcher(expression);
        while(matcher.find()){
            //System.out.println("expression = " + expression);
            expression = expression.replace(matcher.group(),eval(matcher.group(1),matcher.group(2),matcher.group(3)));
        }
        if(!expression.trim().equals("true") && !expression.trim().equals("false"))
            return isTrue(expression);
        else
            return expression;
    }

    private static String eval(String value1,String operator, String value2) {
        switch (operator){
            case "=":
                return String.valueOf(value1.equals(value2));
            case "<>":
                return String.valueOf(!value1.equals(value2));
            case "<":
                return String.valueOf(Double.parseDouble(value1)<Double.parseDouble(value2));
            case ">":
                return String.valueOf(Double.parseDouble(value1)>Double.parseDouble(value2));
            case "<=":
                return String.valueOf(Double.parseDouble(value1)<=Double.parseDouble(value2));
            case ">=":
                return String.valueOf(Double.parseDouble(value1)>=Double.parseDouble(value2));
            case "like":
                return like(value1,value2);
            case "and":
                return String.valueOf(Boolean.parseBoolean(value1) && Boolean.parseBoolean(value2));
            case "or":
                return String.valueOf(Boolean.parseBoolean(value1) || Boolean.parseBoolean(value2));
        }
        return "false";
    }
    private static String like(String value1,String value) {
        if(value.endsWith("%") && value.startsWith("%"))
            return String.valueOf(value1.contains(value.substring(1,value.length() - 1)));
        else if(value.startsWith("%")) {
            return String.valueOf(value1.endsWith(value.substring(1)));
        }else if(value.endsWith("%")) {
            return String.valueOf(value1.startsWith(value.substring(0,value.length() - 1)));
        }else
            return String.valueOf(value1.equals(value));
    }
}
