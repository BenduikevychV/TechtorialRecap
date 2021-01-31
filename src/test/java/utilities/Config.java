package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties;

    public static void readFile(String fileName) throws IOException {

        String path = "src/test/resources/config/"+fileName+".properties";
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }


    }

    public static String getProperty(String fileName, String key) throws IOException {
        readFile(fileName);
        return properties.getProperty(key);
    }
}
