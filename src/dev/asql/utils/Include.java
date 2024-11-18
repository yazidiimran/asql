package dev.asql.utils;

import dev.asql.ASQL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Include {
    public static void trait(String s) throws Exception {
        Matcher matcher= Pattern.compile("include\\s+\\'(.*)\\'\\s*;").matcher(s);
        if(matcher.matches()) {
            s = inc(matcher.group(1));
            ASQL.execute(s);
        }else{
            throw new Exception("Include statement syntax error !");
        }
    }

    private static String inc(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner input = new Scanner(file);
        String data = "";
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if(!line.startsWith("//"))
                data += line;
        }
        input.close();
        return data;
    }
}
