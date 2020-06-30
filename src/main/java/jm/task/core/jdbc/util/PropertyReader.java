package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String read(String parameter) {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(inputStream);
            return properties.getProperty(parameter);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
