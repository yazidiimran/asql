package dev.asql.select;

import java.util.ArrayList;
import java.util.List;

public class Join {
    public static void trait(){
        return;
    }

    public static List<List<String>> inner(List<List<String>> file1,List<List<String>> file2,Integer ix1,Integer ix2) throws Exception {
        List<List<String>> file = new ArrayList<>();
        for(List<String> f1:file1){
            for(List<String> f2:file2){
                if(f1.get(ix1).equals(f2.get(ix2))){
                    List<String> row = new ArrayList<>();
                    for(String f11:f1){
                        row.add(f11);
                    }
                    for(String f22:f2){
                        row.add(f22);
                    }
                    file.add(row);
                }
            }
        }
        return file;
    }

    public static List<List<String>> left(List<List<String>> file1,List<List<String>> file2,Integer ix1,Integer ix2) throws Exception {
        List<List<String>> file = new ArrayList<>();
        Integer file2ColumnLength = file2.get(0).size();
        for(List<String> f1:file1){
            for(List<String> f2:file2){
                if(f1.get(ix1).equals(f2.get(ix2))){
                    List<String> row = new ArrayList<>();
                    for(String f11:f1){
                        row.add(f11);
                    }
                    for(String f22:f2){
                        row.add(f22);
                    }
                    file.add(row);
                }
            }
        }
        for(List<String> f1:file1){
            if(!search(f1.get(ix1),ix2,file2)){
                List<String> row = new ArrayList<>();
                for(String f11:f1){
                    row.add(f11);
                }
                for (int i = 0; i < file2ColumnLength; i++) {
                    row.add(null);
                }
                file.add(row);
            }
        }
        return file;
    }


    public static List<List<String>> right(List<List<String>> file1,List<List<String>> file2,Integer ix1,Integer ix2) throws Exception {
        List<List<String>> file = new ArrayList<>();
        Integer file1ColumnLength = file1.get(0).size();

        for(List<String> f1:file1){
            for(List<String> f2:file2){
                if(f1.get(ix1).equals(f2.get(ix2))){
                    List<String> row = new ArrayList<>();
                    for(String f11:f1){
                        row.add(f11);
                    }
                    for(String f22:f2){
                        row.add(f22);
                    }
                    file.add(row);
                }
            }
        }

        for(List<String> f2:file2){
            if(!search(f2.get(ix2),ix1,file1)){
                List<String> row = new ArrayList<>();
                for (int i = 0; i < file1ColumnLength; i++) {
                    row.add(null);
                }
                for(String f22:f2){
                    row.add(f22);
                }
                file.add(row);
            }
        }
        return file;
    }

    public static List<List<String>> outer(List<List<String>> file1,List<List<String>> file2,Integer ix1,Integer ix2) throws Exception {
        List<List<String>> file = new ArrayList<>();
        Integer file1ColumnLength = file1.get(0).size();
        Integer file2ColumnLength = file2.get(0).size();
        for(List<String> f1:file1){
            for(List<String> f2:file2){
                if(f1.get(ix1).equals(f2.get(ix2))){
                    List<String> row = new ArrayList<>();
                    for(String f11:f1){
                        row.add(f11);
                    }
                    for(String f22:f2){
                        row.add(f22);
                    }
                    file.add(row);
                }
            }
        }
        for(List<String> f1:file1){
            if(!search(f1.get(ix1),ix2,file2)){
                List<String> row = new ArrayList<>();
                for(String f11:f1){
                    row.add(f11);
                }
                for (int i = 0; i < file2ColumnLength; i++) {
                    row.add(null);
                }
                file.add(row);
            }
        }
        for(List<String> f2:file2){
            if(!search(f2.get(ix2),ix1,file1)){
                List<String> row = new ArrayList<>();
                for (int i = 0; i < file1ColumnLength; i++) {
                    row.add(null);
                }
                for(String f22:f2){
                    row.add(f22);
                }
                file.add(row);
            }
        }
        return file;
    }

    public static Boolean search(String key,Integer index,List<List<String>> file){
        for(List<String> f:file){
            if(key.equals(f.get(index)))
                return true;
        }
        return false;
    }


}
