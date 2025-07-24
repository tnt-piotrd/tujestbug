package utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class ConfigurationLoader {
    private static volatile ConfigurationLoader instance;
    private static final String CONFIG_FILE_PATH = "config/config.properties";
    private Properties properties;

    private ConfigurationLoader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input == null) {
                throw new IllegalArgumentException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static ConfigurationLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigurationLoader.class) {
                if (instance == null) {
                    instance = new ConfigurationLoader();
                }
            }
        }
        return instance;
    }

    public String getBaseUrl() {
        return properties.getProperty("env.base_url");
    }

    public String getResolution() {
        return properties.getProperty("browser.resolution");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("browser.headless"));
    }

    public Duration getImplicitWait() {
        int waitTime = Integer.parseInt(properties.getProperty("browser.implicit_wait"));
        return Duration.ofSeconds(waitTime);
    }
}
