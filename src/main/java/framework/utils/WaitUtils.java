package framework.utils;

import framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static framework.config.ConfigManager.getTimeout;

public final class WaitUtils {

    private static final Logger log = LoggerFactory.getLogger(WaitUtils.class);

    private WaitUtils() {
    }

    /**
     * Returns a default timeout from config.
     */
    public static WebDriverWait defaultWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(getTimeout()));
    }

    /**
     * Returns a custom timeout (seconds).
     * @param timeoutSeconds custom timeout in seconds
     */
    public static WebDriverWait customWait(long timeoutSeconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Waits until the element locater is visible.
     * @param locator By locator
     * @return the visible WebElement
     */
    public static WebElement waitForVisibility(By locator) {
        log.debug("Waiting for visibility of: {}", locator);
        return defaultWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the provided {@code element} is visible.
     * @return the visible element
     */
    public static WebElement waitForVisibility(WebElement element) {
        log.debug("Waiting for visibility of element: {}", element);
        return defaultWait().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until all elements are visible.
     * @return list of visible elements
     */
    public static List<WebElement> waitForAllVisible(By locator) {
        log.debug("Waiting for all elements visible: {}", locator);
        return defaultWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


    /**
     * Waits until the element is clickable (visible + enabled).
     */
    public static WebElement waitForClickability(By locator) {
        log.debug("Waiting for element to be clickable: {}", locator);
        return defaultWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the provided element is clickable.
     */
    public static WebElement waitForClickability(WebElement element) {
        return defaultWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element is present in the DOM (may not be visible).
     */
    public static WebElement waitForPresence(By locator) {
        log.debug("Waiting for presence of element: {}", locator);
        return defaultWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits until the page title contains the given substring.
     */
    public static void waitForTitleContains(String titleFragment) {
        log.debug("Waiting for title to contain: '{}'", titleFragment);
        defaultWait().until(ExpectedConditions.titleContains(titleFragment));
    }

    /**
     * Waits until the current URL contains the given substring.
     */
    public static void waitForUrlContains(String urlFragment) {
        log.debug("Waiting for URL to contain: '{}'", urlFragment);
        defaultWait().until(ExpectedConditions.urlContains(urlFragment));
    }

    /**
     * Waits until the element's text equals with default wait.
     */
    public static void waitForTextToBe(WebElement element, String expectedText) {
        log.debug("Waiting for text '{}' on element", expectedText);
        defaultWait().until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    /**
     * Waits until the element is invisible / removed from the DOM.
     */
    public static void waitForInvisibility(By locator) {
        log.debug("Waiting for invisibility of: {}", locator);
        defaultWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
