package dev.asql;
import dev.asql.select.Select;
import dev.asql.utils.Include;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASQL {

    public static List<String[]> file ;
    public static String fileSeparator = ",";
    public static Integer fileColumnsNumber;
    public static Integer fileRowNumber;
    public static String exportPath;


    public static void  execute(String inst) throws Exception {
        Matcher matcher = Pattern.compile("((set|echo|call|include).*?;)|(procedure.*end;)|(if.*?endif;)|(for.*endfor;)|(foreach.*endforeach;)").matcher(inst);
        while(matcher.find()){
            //System.out.println(matcher.group());
            trait(matcher.group());
        }
    }

    public static void trait(String inst) throws Exception {
        if(Pattern.compile("select.*").matcher(inst.trim()).matches()){
            Select.trait(inst);
        }else if(Pattern.compile("(set|unset).*").matcher(inst.trim()).matches()){
            Set.trait(inst);
        }else if(Pattern.compile("echo.*").matcher(inst.trim()).matches()){
            Echo.trait(inst);
        }else if(Pattern.compile("call.*").matcher(inst.trim()).matches()){
            Call.trait(inst);
        }else if(Pattern.compile("for.*endfor;").matcher(inst.trim()).matches()){
            For.trait(inst);
        }else if(Pattern.compile("foreach.*endforeach;").matcher(inst.trim()).matches()){
            Foreach.trait(inst);
        }else if(Pattern.compile("if.*").matcher(inst.trim()).matches()){
            If.trait(inst);
        }else if(Pattern.compile("include.*").matcher(inst.trim()).matches()){
            Include.trait(inst);
        }else if(Pattern.compile("procedure.*").matcher(inst.trim()).matches()){
            Procedure.trait(inst);
        }else{
            throw new Exception("Syntax error ! type 'help' for help");
        }
    }
}
