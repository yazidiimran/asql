package dev.asql;

import dev.asql.functions.General;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Call {
    public static void trait(String s) throws Exception {
        String regex="call\\s+(?<fname>\\w+)\\((?<fparams>(\\$\\w+)=(.*))?\\)\\s*;\\s*";
        Matcher matcher= Pattern.compile(regex).matcher(s);
        if(matcher.matches()){
            String fname = matcher.group("fname");
            String fparams = matcher.group("fparams");
            if(fname.equals("_variables")){
                General._variables();
            }else if(fname.equals("_procedures")){
                General._procedures();
            }else{
                s = Procedure.procedures.get(fname);
                if (s == null){
                    throw new Exception(fname+" function is not exists !");
                }else{
                    setParam(fparams);
                    ASQL.execute(s);
                    unSetParam(fparams);
                }
            }
        }else{
            throw new Exception("Call syntax error !");
        }
    }

    public static void setParam(String fparams) throws Exception {
        for (String p: fparams.split(",")){
            Matcher matcher = Pattern.compile("(\\$\\w+)=(.*)").matcher(p);
            if(matcher.matches()){
                Set.variables.put(matcher.group(1),matcher.group(2));
            }else{
                throw new Exception("Procedure params syntax error");
            }
        }
    }

    public static void unSetParam(String fparams) throws Exception {
        for (String p: fparams.split(",")){
            Matcher matcher = Pattern.compile("(\\$\\w+)=(.*)").matcher(p);
            if(matcher.matches()){
                Set.variables.remove(matcher.group(1));
            }else{
                throw new Exception("Procedure params syntax error");
            }
        }
    }


}
