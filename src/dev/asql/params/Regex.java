package dev.asql.params;

import java.util.regex.Matcher;

public class Regex {
    public static String RGX_SELECT = "select\\s+(((\\*|F\\d+|(count|sum|min|max|avg)\\(F\\d+\\)),?)+)\\s+";
    //public static String RGX_FROM = "from\\s+\\'(.+?)\\'\\s+(skip\\s+line\\s(\\d+))?";
    public static String RGX_FROM = "from\\s+\\'(.+?)\\'(\\s+skip\\s+line\\s(\\d+))?";
    public static String RGX_JOIN = "(\\s+(inner|outer|left|right)\\s+join\\s+)?";
    public static String RGX_WHERE = "(\\s+where\\s(F\\d+(=|<|>|<>)(\\d+|\\'\\w+\\')))?";
    public static String RGX_GROUP = "(\\s+group\\s+by\\s+)?";
    public static String RGX_HAVING = "(\\s+having\\s+)?";
    public static String RGX_ORDER = "(\\s+order\\s+by\\s+)?";
    public static String RGX_EXPORT = "(\\s+export\\s+to\\s+\\'(.*?)\\'\\s*)?";
    public static String RGX_END = "\\s*;";
    public static String REGEX = RGX_SELECT+RGX_FROM+RGX_JOIN+RGX_WHERE+RGX_GROUP+RGX_HAVING+RGX_ORDER+RGX_EXPORT+RGX_END;

    public static void groups(Matcher matcher){
        //System.out.println(Regex.REGEX);
        int i = 0;
        while (matcher.find()) {
            for (int j = 0; j <= matcher.groupCount(); j++) {
                System.out.println("------------------------------------");
                System.out.println("Group " + i + ": " + matcher.group(j));
                i++;
            }
        }
    }
}
