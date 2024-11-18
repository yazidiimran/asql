package dev.asql;

import dev.asql.calcul.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Echo {
    public static void trait(String s) throws Exception {
        Matcher matcher= Pattern.compile("echo\\s*(.*)\\s*;").matcher(s);
        if(matcher.matches()){
            System.out.println(Expression.get(matcher.group(1)));
        }else{
            throw new Exception("Echo function syntax error !");
        }
    }
}
