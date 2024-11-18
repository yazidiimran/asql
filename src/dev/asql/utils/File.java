package dev.asql.utils;

import dev.asql.params.Alias;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class File {

    public static void write(String path,String str) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(path, "UTF-8");
        writer.print(str);
        writer.close();
    }

    public static List<List<String>> read(String path, String separator, boolean skipFirstLine, boolean withTitles) throws IOException {
        Scanner myReader = new Scanner(Paths.get(path));
        StringBuilder data = new StringBuilder();
        int i=0;
        while (myReader.hasNextLine()) {
            if(skipFirstLine && i==0){
                String titles = myReader.nextLine();
                Alias.addLine(titles,separator);
            }
            data.append(myReader.nextLine()).append("\n");
            i++;
        }
        myReader.close();
        String[] arr = data.toString().split("\n");
        List<List<String>> output = new ArrayList<>();
        for(String champ:arr){
            String[] line = champ.split(separator);
            List<String> row = new ArrayList<>();
            for(String l:line){
                row.add(l);
            }
            output.add(row);
        }
        return output;
    }
}
