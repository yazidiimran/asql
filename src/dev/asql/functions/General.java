package dev.asql.functions;

import dev.asql.Procedure;
import dev.asql.Set;

public class General {
    public static void _variables(){
        System.out.println("------- ALL VARAIBLES -------");
        Set.variables.forEach((key, value) -> System.out.println(key + " => " + value));
    }

    public static void _procedures(){
        System.out.println("------- ALL PROCEDURES -------");
        Procedure.procedures.forEach((key, value) -> System.out.println(key + " => " + value));
    }
}
