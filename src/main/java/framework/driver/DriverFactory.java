package framework.driver;

import framework.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * DriverFactory – creates browser-specific WebDriver instances.
 * Bonigarcia WebDriverManager for automatic binary management</li>
 * Common options (sandbox, extensions…)
 */
public final class DriverFactory {

    private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    /**
     * Creates and returns a new Webdriver instance for the configured browser.
     * @return initialised WebDriver
     */
    public static WebDriver createDriver() {
        String browser = ConfigManager.getBrowser();
        log.info("Creating WebDriver for browser: '{}'", browser);

        WebDriver driver = switch (browser) {
            case "firefox" -> createFirefoxDriver();
            case "safari" -> createSafariDriver();
            case "edge" -> createEdgeDriver();
            case "chrome" -> createChromeDriver();
            default -> {
                log.warn("Unknown browser '{}'; falling back to Chrome.", browser);
                yield createChromeDriver();
            }
        };

        configureTimeouts(driver);
        driver.manage().window().maximize();
        log.info("WebDriver created successfully [thread={}]", Thread.currentThread().getId());
        return driver;
    }

    // Browser-specifics

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--disable-extensions",
                "--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920", "--height=1080");
        return new FirefoxDriver(options);
    }

    private static WebDriver createSafariDriver() {
        WebDriverManager.safaridriver().setup();
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-extensions");
        return new EdgeDriver(options);
    }

    private static void configureTimeouts(WebDriver driver) {
        int timeout = ConfigManager.getTimeout();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout * 3L));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout * 2L));
    }
}
