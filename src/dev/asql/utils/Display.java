package dev.asql.utils;
import dev.asql.params.Alias;

import java.util.List;

public class Display {

    public static void show(List<List<String>> file)   {
        if(file.isEmpty())
            System.out.println("Empty data.");

        for (String s: Alias.selected.values()) {
            s = Alias.alias.get(s)!=null?Alias.alias.get(s):s;
            System.out.print(s+", ");
        }
        System.out.println();
        for(List<String> row:file){
            for(String cell:row){
                System.out.print(cell+", ");
            }
            System.out.println();
        }
    }
}
