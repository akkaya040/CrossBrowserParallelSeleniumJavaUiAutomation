package framework.pages;

import framework.driver.DriverManager;
import framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public abstract class BasePage {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Returns the WebDriver bound to the current thread.
     * Driver access (thread-safe)
     *
     */
    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    protected JavascriptExecutor js() {
        return (JavascriptExecutor) driver();
    }

    protected Actions actions() {
        return new Actions(driver());
    }

    public void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        driver().get(url);
    }

    public String getCurrentUrl() {
        return driver().getCurrentUrl();
    }

    public String getPageTitle() {
        return driver().getTitle();
    }

    protected WebElement findElement(By locator) {
        log.debug("findElement: {}", locator);
        return WaitUtils.waitForPresence(locator);
    }

    protected List<WebElement> findElements(By locator) {
        log.debug("findElements: {}", locator);
        try {
            return driver().findElements(locator);
        } catch (Exception e) {
            log.debug("findElements hata döndü – boş liste: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Element interaction

    protected void click(By locator) {
        log.debug("Clicking element: {}", locator);
        WaitUtils.waitForClickability(locator).click();
    }

    protected void click(WebElement element) {
        log.debug("Clicking WebElement");
        WaitUtils.waitForClickability(element).click();
    }

    protected void type(By locator, String text) {
        log.debug("Typing '{}' into: {}", text, locator);
        WebElement field = WaitUtils.waitForVisibility(locator);
        field.clear();
        field.sendKeys(text);
    }

    protected String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return WaitUtils.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            log.debug("Element not visible: {} – {}", locator, e.getMessage());
            return false;
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitUntilPageLoad(int... timeout) {
        int finalTimeout = (timeout.length > 0) ? timeout[0] : 30;
        WaitUtils.customWait(finalTimeout)
                .until(webDriver ->
                        ((JavascriptExecutor) driver())
                                .executeScript("return document.readyState")
                                .toString()
                                .equals("complete"));
    }

    protected void scrollToElement(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        js().executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        pause(1000);
    }

    protected void scrollIntoView(WebElement element) {
        js().executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);
        pause(500);
    }

    protected void scrollToTop() {
        js().executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        js().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void jsClickWithQuery(WebElement element) {
        js().executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", element);
    }

    protected void jsClickWithQuery(By locator) {
        jsClickWithQuery(findElement(locator));
    }

    protected void jsClick(WebElement element) {
        js().executeScript("arguments[0].click();", element);
    }

    protected void jsClick(By locator) {
        jsClick(findElement(locator));
    }

    protected String jsGetText(WebElement element) {
        return (String) js().executeScript("return arguments[0].innerText;", element);
    }

    protected void waitForTitleContains(String fragment) {
        WaitUtils.waitForTitleContains(fragment);
    }

    protected void waitForUrlContains(String fragment) {
        WaitUtils.waitForUrlContains(fragment);
    }

    protected void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void hoverElement(WebElement element) {
        actions().moveToElement(element).perform();
    }

    protected void hoverElement(By locator) {
        hoverElement(findElement(locator));
    }

    protected boolean isNullOrEmpty(String param) {
        return param == null || param.trim().isEmpty();
    }

}
