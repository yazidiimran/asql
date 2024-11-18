import dev.asql.ASQL;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            if(args.length==0){
                Console.trait();
            }else{
                File file = new File(args[0]);
                Scanner input = new Scanner(file);
                StringBuilder data = new StringBuilder();
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    if(!line.trim().startsWith("//")){
                        data.append(line);
                    }
                }
                input.close();
                ASQL.execute(data.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
