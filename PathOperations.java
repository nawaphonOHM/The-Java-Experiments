import java.nio.file.Paths;
import java.nio.file.Path;;

public class PathOperations{
    public static void main(String[] args){
        Path path = Paths.get("/home/joe/foo");

        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n%n", path.getRoot());

        Path path2 = Paths.get("sally/bar");

        System.out.format("toString: %s%n", path2.toString());
        System.out.format("getFileName: %s%n", path2.getFileName());
        System.out.format("getName(0): %s%n", path2.getName(0));
        System.out.format("getNameCount: %d%n", path2.getNameCount());
        System.out.format("subpath(0,2): %s%n", path2.subpath(0, 2));
        System.out.format("getParent: %s%n", path2.getParent());
        System.out.format("getRoot: %s%n", path2.getRoot());
    }
}
