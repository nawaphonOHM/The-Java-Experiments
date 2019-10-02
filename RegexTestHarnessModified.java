import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarnessModified{
    public static void main(String[] args){
        Console console = System.console();
        if(console == null){
            System.err.println("No console.");
            System.exit(1);
        }
        while(true){
            Pattern pattern = 
                Pattern.compile(
                        console.readLine("%nEnter your regex: "), 
                        Pattern.CASE_INSENSITIVE
                    );
            
            Matcher mather = 
                pattern.matcher(console.readLine("Enter input string to search: "));
            
            
            boolean found = false;
            while(mather.find()){
                console.format("I found the text" + 
                    " \"%s\" starting at " + 
                    "index %d and ending at index %d.%n", 
                    mather.group(), 
                    mather.start(), 
                    mather.end()
                );
                found = true;
            }
            if(!found){
                console.format("No match found.%n");
            }
        }
    }
}