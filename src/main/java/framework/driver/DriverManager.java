package framework.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DriverManager – manages a {@link WebDriver} instance per thread.

 * Lifecycle:
 * DriverManager.initDriver(); // call once per scenario thread (in @Before hook)
 * DriverManager.getDriver(); // access anywhere in that thread
 * DriverManager.quitDriver(); // call once per scenario thread (in @After hook)
 */
public final class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void initDriver() {
        if (driverThreadLocal.get() != null) {
            log.warn("Driver already initialised for thread [{}]. Skipping re-init.",
                    Thread.currentThread().getId());
            return;
        }
        WebDriver driver = DriverFactory.createDriver();
        driverThreadLocal.set(driver);
        log.debug("Driver stored in ThreadLocal [thread={}]", Thread.currentThread().getId());
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialised for thread [" +
                            Thread.currentThread().getId() +
                            "]. Ensure initDriver() is called in the @Before hook.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                log.info("WebDriver quit successfully [thread={}]", Thread.currentThread().getId());
            } catch (Exception e) {
                log.error("Error while quitting WebDriver", e);
            } finally {
                driverThreadLocal.remove();
            }
        } else {
            log.warn("quitDriver() called but no driver found for thread [{}]",
                    Thread.currentThread().getId());
        }
    }

    public static boolean isDriverAlive() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null)
            return false;
        try {
            driver.getTitle(); // lightweight liveness check
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
