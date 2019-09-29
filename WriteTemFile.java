import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

import java.io.IOException;

public class WriteTemFile{
    public static void main(String[] args){
        try{
            Path tempFile = Files.createTempFile(null, ".myapp");
            System.out.format(
                    "The temporary file" + " has been created: %s%n", 
                    tempFile
                );
        } catch (IOException x){
            System.err.format("IOException %s%n", x);
        }
    }
}