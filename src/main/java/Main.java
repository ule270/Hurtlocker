import org.apache.commons.io.IOUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {
    public static String readRawDataToString() throws Exception{
        //Path creates an object that may be used to locate a file in system;
        Path path = get(ClassLoader.getSystemResource("RawData.txt").toURI());
        return new String(readAllBytes(get(path.toUri())));
    }

    public static void main(String[] args) throws Exception{
        ItemParser IP = new ItemParser();
        IP.createItem(readRawDataToString());
        System.out.println(IP.groceryListConstructor());

    }
}
