package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс формирования свойств соединения с БД по конфигурационному файлу.
 */
public class ConnectionProperty {
    public static final String CONFIG_NAME = "config.properties";
    public static final Properties PROPERTY_CONFIG = new Properties();
    private static boolean loaded = false;

    public ConnectionProperty() throws IOException {
        loadProperties();
    }

    public static synchronized void loadProperties() throws IOException {
        if (loaded) {
            return;
        }

        ClassLoader classLoader = ConnectionProperty.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("config/" + CONFIG_NAME)) {
            if (inputStream == null) {
                throw new IOException("Файл config/config.properties не найден в classpath");
            }
            PROPERTY_CONFIG.load(inputStream);
            loaded = true;
        }
    }

    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}
