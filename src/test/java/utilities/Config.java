package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties;

    static {
        try{
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/configuration.properties");
            properties.load(file);
            file.close();
        }catch (IOException e){
            System.out.println("Configuration file not found");
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

}
