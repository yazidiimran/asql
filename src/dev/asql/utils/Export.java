package dev.asql.utils;

import dev.asql.ASQL;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Export {
    public static void trait() throws FileNotFoundException, UnsupportedEncodingException {
        String s = "";
        for(String[] arr: ASQL.file){
            for(String c:arr){
                s+=c+ ",";
            }
            s+="\n";
        }
        File.write(ASQL.exportPath,s);
    }
}
