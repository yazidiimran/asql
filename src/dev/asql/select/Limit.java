package dev.asql.select;
import java.util.List;

public class Limit {
    public static List<List<String>> get(List<List<String>> file, int formIndex,int toIndex) {
        return file.subList(formIndex,toIndex);
    }
}
