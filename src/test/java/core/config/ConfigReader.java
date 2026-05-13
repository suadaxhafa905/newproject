package core.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try {
            String environment = System.getProperty("environment", "test");

            String configFile =
                    "config-" + environment + ".properties";

            InputStream input =
                    ConfigReader.class
                            .getClassLoader()
                            .getResourceAsStream(configFile);

            if (input == null) {
                throw new RuntimeException(
                        "Config file not found: " + configFile
                );
            }

            prop.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load configuration file",
                    e
            );
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
/*
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {

        try {

            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException(
                        "config.properties file not found"
                );
            }

            prop.load(input);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load config.properties",
                    e
            );
        }
    }

    public static String get(String key) {

        String value = prop.getProperty(key);

        if (value == null) {
            throw new RuntimeException(
                    "Property not found: " + key
            );
        }

        return value.trim();
    }
}

 */

