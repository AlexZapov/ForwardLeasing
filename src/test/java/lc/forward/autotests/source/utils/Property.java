package lc.forward.autotests.source.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Property {

    public static String readProperty(String property) {
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            prop.load(new FileInputStream(new File("src/test/resources/config.properties")));

            value = prop.getProperty(property);

            if (value == null || value.isEmpty()) {
                throw new Exception("Value not set or empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}
