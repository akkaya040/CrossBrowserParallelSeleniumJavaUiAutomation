package framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager - Singleton configuration reader.
 */
public final class ConfigManager {

    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private ConfigManager() {}

    /**
     * Returns the value for key.
     * System properties have higher priority than file values.
     *
     * @param key the property key
     * @return the resolved value
     */
    public static String get(String key) {
        // System/Maven property (-Dkey=value) takes highest priority
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        String fileValue = properties.getProperty(key);
        return fileValue != null ? fileValue.trim() : null;
    }

    /**
     * Returns the value for kry, falling back to defaultValue.
     * @return resolved value or defaultValue
     */
    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }

    /** Convenience helper for the configured base URL. */
    public static String getBaseUrl() {
        return get("baseUrl", "https://insiderone.com");
    }

    /** Convenience helper for the configured browser name (lowercase). */
    public static String getBrowser() {
        return get("browser", "chrome").toLowerCase();
    }

    /** Convenience helper for the implicit/explicit wait timeout (seconds). */
    public static int getTimeout() {
        try {
            return Integer.parseInt(get("timeout", "10"));
        } catch (NumberFormatException e) {
            log.warn("Invalid timeout value in config – defaulting to 10 seconds");
            return 10;
        }
    }

    // Private helpers
    private static void loadProperties() {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                log.warn("'{}' not found on classpath; relying on system properties only.", CONFIG_FILE);
                return;
            }
            properties.load(is);
            log.info("Loaded configuration from '{}'", CONFIG_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }
    }
}
