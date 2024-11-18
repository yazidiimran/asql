package dev.asql.select;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Order {
    public static void trait(){

    }

    public static List<List<String>> desc(List<List<String>> file, Integer ix){
        Collections.sort(file, new Comparator<List<String>> () {
            @Override
            public int compare(List<String> a, List<String> b) {
                return b.get(ix).compareTo(a.get(ix));
            }
        });
        return  file;
    }

    public static List<List<String>> asc(List<List<String>> file,  Integer ix) {
        Collections.sort(file, new Comparator<List<String>> () {
            @Override
            public int compare(List<String> a, List<String> b) {
                return a.get(ix).compareTo(b.get(ix));
            }
        });
        return  file;
    }
}
