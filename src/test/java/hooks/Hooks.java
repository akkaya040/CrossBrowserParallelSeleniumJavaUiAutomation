package hooks;

import framework.driver.DriverManager;
import framework.utils.ScreenshotUtils;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hooks – Cucumber lifecycle hooks for setup, teardown, and failure handling.
 * Before – initialise a thread-local WebDriver
 * After – quit the driver and log scenario outcome
 */
public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    /**
     * Initialises the WebDriver for the current thread.
     * @param scenario the active Cucumber scenario (for logging)
     */
    @Before(order = 0)
    public void setUp(Scenario scenario) {
        log.info("──── Starting scenario: '{}' [thread={}] ────",
                scenario.getName(), Thread.currentThread().getId());
        DriverManager.initDriver();
        Allure.label("thread", String.valueOf(Thread.currentThread().getId()));
    }

    /**
     * Used for tasks to be performed 'before' a step.
     */
    //@BeforeStep
    public void beforeEachStep(Scenario scenario) {

    }

    /**
     * Used for tasks to be performed 'after' a step.
     */
    //@AfterStep
    public void afterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.warn("Step FAILED in scenario: '{}' – capturing screenshot", scenario.getName());
            String screenshotLabel = "Failed Step – " + scenario.getName();
            ScreenshotUtils.takeScreenshotAndAttach(screenshotLabel);
        }
    }

    /**
     * Takes a final screenshot on scenario failure, logs the result, and
     * quits the driver.
     */
    @After(order = 0)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                log.error("Scenario FAILED: '{}' – attaching failure screenshot", scenario.getName());
                ScreenshotUtils.takeScreenshotAndAttach("Scenario Failure Screenshot");
            } else {
                log.info("Scenario PASSED: '{}'", scenario.getName());
            }
        } finally {
            DriverManager.quitDriver();
            log.info("──── Finished scenario: '{}' ────", scenario.getName());
        }
    }
}
