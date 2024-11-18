package dev.asql;

import dev.asql.calcul.Logique;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class If {
    public static void trait(String s) throws Exception {
        String regex = "if\\s*(.*)\\s*then\\s*(.*)\\s*else\\s*(.*)\\s*endif;";
        Matcher matcher= Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            if(Boolean.parseBoolean(Logique.isTrue(matcher.group(1)))){
                ASQL.execute(matcher.group(2));
            }else{
                ASQL.execute(matcher.group(3));
            }
        }else{
            throw new Exception("If statement error syntax !");
        }
    }
}
