package dev.asql.calcul;

import dev.asql.utils.Calcul;
import dev.asql.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    public static String get(String s) throws Exception {
        Matcher matcher= Pattern.compile("(\\'|\\\")(.*)\\1").matcher(s);
        if(matcher.matches()){
            return  matcher.group(2);
        }

        matcher= Pattern.compile("\\$\\w+").matcher(s);
        while (matcher.find()){
            String v = Set.variables.get(matcher.group());
            if(v == null){
                throw new Exception("Variable "+matcher.group()+" is not found !");
            }else{
                s = s.replace(matcher.group(),v);
            }
        }

        matcher = Pattern.compile("([\\d.()+*/\\-]+)").matcher(s);
        while(matcher.find()){
            s = s.replace(matcher.group(1),String.valueOf(Calcul.arithmethique(matcher.group(1))));
        }
        return s;
    }
}
