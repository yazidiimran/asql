import dev.asql.ASQL;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Console {
    public static void trait()  {

        System.out.println("Welcome to the ASQL Monitor.");
        System.out.println("Version 1.0 - Copyright 2023");
        System.out.println("Type 'help' for help, 'exit' to quit");
        Scanner in = new Scanner(System.in);
        String s;
        while(true){
            System.out.print("ASQL> ");
            s = in.nextLine();
            if(s.equals("exit")){
                System.out.println("Bye Bye :)");
                System.exit(0);
            } else if (s.equals("help")) System.out.println("Help: Please visit our website https://doc.asql.dev");
            else{
                try {
                    ASQL.execute(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}