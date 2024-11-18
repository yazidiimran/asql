package dev.asql.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calcul {



    public static double  arithmethique(String expression){
        return Double.parseDouble(complexEquation(expression));
    }
    public static String  complexEquation(String expression){
        //System.out.println(expression);
        String regex = "(\\((\\d+(\\.\\d+)?)([+*/\\-](\\d+(\\.\\d+)?))+\\))" ;
        Matcher matcher = Pattern.compile(regex).matcher(expression);
        while(matcher.find()) {
            expression = expression.replace(matcher.group(1),simpleEquation(matcher.group(1)));
        }
        //System.out.println( expression);
        if(Pattern.compile("(\\d+(\\.\\d+)?)([+*/\\-](\\d+(\\.\\d+)?))+").matcher(expression).matches())
            return simpleEquation(expression.replace("(","").replace(")",""));
        if(Pattern.compile("\\d+(\\.\\d+)?").matcher(expression).matches())
            return expression;
        return complexEquation(expression);
    }





    public static String simpleEquation(String expression){
        //System.out.println(expression);
        Matcher m = Pattern.compile("(\\d+(\\.\\d+)?)([*/])(\\d+(\\.\\d+)?)").matcher(expression);
        if (m.find()) {
            String s= m.group();
            Double a= Double.valueOf(m.group(1));
            String  o=m.group(3);
            Double b= Double.valueOf(m.group(4));
            return simpleEquation(expression.replace(s,String.valueOf(eval(a,o,b))));
        }
        m = Pattern.compile("(\\d+(\\.\\d+)?)([+\\-])(\\d+(\\.\\d+)?)").matcher(expression);
        if (m.find()){
            String s= m.group();
            Double a= Double.valueOf(m.group(1));
            String  o=m.group(3);
            Double b= Double.valueOf(m.group(4));
            return simpleEquation(expression.replace(s,String.valueOf(eval(a,o,b))));
        }
        return expression.replace("(","").replace(")","");
    }

    public static Double eval(Double a, String operator, Double b){
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> null;
        };
    }
}
