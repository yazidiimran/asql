package dev.asql.select;

import dev.asql.ASQL;

public class From {

    public static void trait() throws Exception {
        //FQL.file = File.StrToArr(File.read(FQL.path),FQL.fileSeparator);
        ASQL.fileColumnsNumber = ASQL.file.get(0).length;
        ASQL.fileRowNumber = ASQL.file.size();
    }
}
