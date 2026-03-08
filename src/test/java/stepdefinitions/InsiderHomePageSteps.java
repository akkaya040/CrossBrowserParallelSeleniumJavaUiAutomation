package stepdefinitions;

import framework.pages.InsiderHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.*;

public class InsiderHomePageSteps {

    private static final Logger log = LoggerFactory.getLogger(InsiderHomePageSteps.class);

    /** Her senaryo thread'i kendi page instance'ını alır. */
    private final InsiderHomePage insiderHomePage = new InsiderHomePage();


    @Given("I navigate to {string}")
    @Step("Given I navigate to '{url}'")
    public void iNavigateTo(String url) {
        log.info("Step: I navigate to '{}'", url);
        insiderHomePage.open(url);
    }

    @Then("the Insider home page should be opened")
    @Step("Then the Insider home page should be opened")
    public void theInsiderHomePageShouldBeOpened() {
        log.info("Step: Verifying Insider home page is opened");

        boolean isLoaded = insiderHomePage.isHomePageLoaded();
        String currentUrl = insiderHomePage.getUrl();
        String title = insiderHomePage.getTitle();

        log.info("Assert homepage loaded → URL: '{}', Title: '{}'", currentUrl, title);
        assertTrue(isLoaded,
                "Insider home page did not load correctly. URL: " + currentUrl + " | Title: " + title);
    }

    @And("the main blocks on the homepage should be visible")
    @Step("And the main blocks on the homepage should be visible")
    public void theMainBlocksOnTheHomepageShouldBeVisible() {
        log.info("Step: Verifying main homepage blocks are visible");

        // Navigation bar
        boolean navVisible = insiderHomePage.isNavigationBarVisible();
        log.info("Navigation bar visible: {}", navVisible);
        assertTrue(navVisible, "Navigation bar is not visible on the homepage");

        // Hero section
        boolean heroVisible = insiderHomePage.isHeroSectionVisible();
        log.info("Hero section visible: {}", heroVisible);
        assertTrue(heroVisible, "Hero/banner section is not visible on the homepage");

        // Footer
        boolean footerVisible = insiderHomePage.isFooterVisible();
        log.info("Footer visible: {}", footerVisible);
        assertTrue(footerVisible, "Footer is not visible on the homepage");

        // Navigation links count
        int navLinksCount = insiderHomePage.getNavigationLinksCount();
        log.info("Number of navigation links found: {}", navLinksCount);
        assertGreaterThanZero(navLinksCount, "No navigation links found on the homepage");

        log.info("All homepage blocks verified successfully ✓");
    }

    @And("the main blocks on the homepage should be invisible")
    @Step("And the main blocks on the homepage should be invisible")
    public void theMainBlocksOnTheHomepageShouldBeInvisible() {
        log.info("Step: Verifying main homepage blocks are invisible");

        boolean navVisible = insiderHomePage.isNavigationBarVisible();
        log.info("Navigation bar visible: {}", navVisible);
        assertFalse(navVisible, "Navigation bar is visible on the homepage");

        log.info("All homepage blocks verified successfully ✓");
    }

}
