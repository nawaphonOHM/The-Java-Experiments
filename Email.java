import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

public class Email{
    private final WatchService watcher;
    private final Path dir;

    Email(Path dir) throws IOException{
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        this.dir = dir;
    }

    void processEvents(){
        for(;;){
            WatchKey key;
            try{
                key = watcher.take();
            } catch(InterruptedException x){ return; }

            for(WatchEvent<?> event : key.pollEvents()){
                WatchEvent.Kind kind = event.kind();

                if(kind == OVERFLOW) continue;

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                try{
                    Path child = dir.resolve(filename);
                    if(!Files.probeContentType(child).equals("text/plain")){
                        System.err.format("New file '%s' is not a plain text file.%n", filename);
                        continue;
                    }
                } catch(IOException x){
                    System.err.println(x);
                    continue;
                }

                System.out.format("Emailing file %s%n", filename);
            }

            boolean valid = key.reset();
            if(!valid) break;
        }
    }

    static void usage(){
        System.err.println("usage: java Email dir");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException{
        if(args.length < 1) usage();

        Path dir = Paths.get(args[0]);
        new Email(dir).processEvents();
    }
}