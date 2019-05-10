package framework.utils;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Allows read config.properties
 *
 * @author Yaroslav Lazakovich
 * @version 1.0
 */
public class PropertyReader {
    private static final Object LOCK = new Object();
    private static PropertyReader instance;
    private static Properties properties;
    private InputStream path = this.getClass().getClassLoader().getResourceAsStream("config.properties");
    // TODO add field for Logger

    public static PropertyReader getInstance() {
        if (instance == null)
            synchronized (LOCK) {
                instance = new PropertyReader();
                instance.loadData();
            }
        return instance;
    }

    private void loadData() {
        properties = new Properties();
        try {
            properties.load(path);
        } catch (FileNotFoundException e) {
            //TODO log.error("loc.err.properties.not.found");
        } catch (IOException e) {
            //TODO log.error("loc.err.properties.file.path");

        }
    }

    public static String getProperty(String property) {
        getInstance();
        return properties.getProperty(property);
    }

    public static String getPropertyOrDefault(String propertyName, String defaultValue) {
        String result = getProperty(propertyName);
        return (null == result) ? defaultValue : result;
    }


    // TODO check work status of PropertyReader
    @Test
    public void testGetProperty() {
        String value = getProperty("url");
        System.out.println(value);
    }
}

