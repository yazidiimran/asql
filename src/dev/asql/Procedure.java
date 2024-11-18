package dev.asql;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Procedure {

    public static HashMap<String,String> procedures = new HashMap<>();
    public static void trait(String s) throws Exception {
        String regex = "procedure\\s+(?<fname>\\w+)\\((?<fparams>\\$\\w+(,\\$\\w+)*)?\\)\\s*begin\\s*(?<fcode>.*)end;";
        Matcher matcher= Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            String fname = matcher.group("fname");
            String fparams = matcher.group("fparams");
            String fcode = matcher.group("fcode");
            procedures.put(fname,fcode);
        }else{
            throw new Exception("Procedure syntax error !");
        }
    }
}
