package framework.utils;

import framework.driver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static final String SCREENSHOT_DIR = "target/screenshots";
    private static final DateTimeFormatter TIMESTAMP_FMT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtils() {
    }


    /**
     * Captures a screenshot for the current thread's driver, saves it to target folder
     * shown in the Allure report attachment
     */
    public static void takeScreenshotAndAttach(String name) {
        try {
            byte[] screenshotBytes = captureScreenshot();
            if (screenshotBytes == null)
                return;

            // Save to disk
            Path saved = saveToDisk(name, screenshotBytes);
            log.info("Screenshot saved: {}", saved.toAbsolutePath());

            // Attach to Allure
            attachToAllure(name, screenshotBytes);
            log.debug("Screenshot attached to Allure: '{}'", name);

        } catch (Exception e) {
            log.error("Failed to capture/attach screenshot: {}", e.getMessage(), e);
        }
    }

    public static void takeFailureScreenshot() {
        takeScreenshotAndAttach("Failure Screenshot");
    }

    /**
     * Returns raw screenshot bytes for the current thread's driver.
     * @return PNG bytes
     */
    public static byte[] captureScreenshot() {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot ts) {
                return ts.getScreenshotAs(OutputType.BYTES);
            }
            log.warn("Current WebDriver does not implement TakesScreenshot");
            return null;
        } catch (Exception e) {
            log.error("Screenshot capture failed: {}", e.getMessage());
            return null;
        }
    }

    private static Path saveToDisk(String name, byte[] bytes) throws IOException {
        Path dir = Paths.get(SCREENSHOT_DIR);
        Files.createDirectories(dir);

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FMT);
        String safeLabel = name.replaceAll("[^a-zA-Z0-9_\\-]", "_");
        String filename = String.format("%s_%s.png", safeLabel, timestamp);

        Path path = dir.resolve(filename);
        Files.write(path, bytes);
        return path;
    }

    private static void attachToAllure(String name, byte[] bytes) {
        Allure.addAttachment(name, "image/png", new ByteArrayInputStream(bytes), ".png");
    }
}
