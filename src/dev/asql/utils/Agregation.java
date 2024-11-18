package dev.asql.utils;

import java.util.ArrayList;
import java.util.List;

public class Agregation {

    public static int search(List<List<String>> resFile,int index,String value){
        if(resFile.isEmpty())
            return -1;
        for (int i = 0; i < resFile.size(); i++) {
            if(resFile.get(i).get(index).equals(value)){
                return i;
            }
        }
        return -1;
    }

    public static List<List<String>> count(List<List<String>> file,int index,int index2){
        List<List<String>> resFile = new ArrayList<>();
        List<String> row;
        int pos = -1;
        for(List<String> f:file){
            pos = search(resFile,0,f.get(index));
            if(pos==-1) {
                row = new ArrayList<>();
                row.add(f.get(index));
                row.add("1");

                resFile.add(row);
            }else{
                row = resFile.get(pos);
                Double value = Double.parseDouble(row.get(1))+1;
                row.set(1,String.valueOf(value));
                resFile.set(pos,row);
            }
        }
        return resFile;
    }
    public static List<List<String>> sum(List<List<String>> file,int index,int index2){
        List<List<String>> resFile = new ArrayList<>();
        List<String> row;
        int pos = -1;
        for(List<String> f:file){
            pos = search(resFile,0,f.get(index));
            if(pos==-1) {
                row = new ArrayList<>();
                row.add(f.get(index));
                row.add(f.get(index2));

                resFile.add(row);
            }else{
                row = resFile.get(pos);
                Double value =Double.parseDouble(row.get(1))+Double.parseDouble(f.get(index2));
                row.set(1,String.valueOf(value));
                resFile.set(pos,row);
            }
        }
        return resFile;
    }
    public static List<List<String>> avg(List<List<String>> file,int index,int index2){
        List<List<String>> resFile = new ArrayList<>();
        List<String> row;
        int pos = -1;
        for(List<String> f:file){
            pos = search(resFile,0,f.get(index));
            if(pos==-1) {
                row = new ArrayList<>();
                row.add(f.get(index));
                row.add(f.get(index2));
                row.add("1");
                resFile.add(row);
            }else{
                row = resFile.get(pos);
                Double value =Double.parseDouble(row.get(1))+Double.parseDouble(f.get(index2));
                Double iteration = Double.parseDouble(row.get(2))+1;
                row.set(2,String.valueOf(iteration));
                row.set(1,String.valueOf(value/iteration));
                resFile.set(pos,row);
            }
        }
        return resFile;
    }
    public static List<List<String>> min(List<List<String>> file,int index,int index2){
        List<List<String>> resFile = new ArrayList<>();
        List<String> row;
        int pos = -1;
        for(List<String> f:file){
            pos = search(resFile,0,f.get(index));
            if(pos==-1) {
                row = new ArrayList<>();
                row.add(f.get(index));
                row.add(f.get(index2));
                resFile.add(row);
            }else{
                row = resFile.get(pos);
                Double value = Math.min(Double.parseDouble(row.get(1)), Double.parseDouble(f.get(index2)));
                row.set(1,String.valueOf(value));
                resFile.set(pos,row);
            }
        }
        return resFile;
    }
    public static List<List<String>> max(List<List<String>> file,int index,int index2){
        List<List<String>> resFile = new ArrayList<>();
        List<String> row;
        int pos = -1;
        for(List<String> f:file){
            pos = search(resFile,0,f.get(index));
            if(pos==-1) {
                row = new ArrayList<>();
                row.add(f.get(index));
                row.add(f.get(index2));
                resFile.add(row);
            }else{
                row = resFile.get(pos);
                Double value = Math.max(Double.parseDouble(row.get(1)), Double.parseDouble(f.get(index2)));
                row.set(1,String.valueOf(value));
                resFile.set(pos,row);
            }
        }
        return resFile;
    }
}
