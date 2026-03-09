package stepdefinitions;

import framework.config.ConfigManager;
import framework.pages.CommonPage;
import framework.pages.InsiderHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.*;

public class InsiderHomePageSteps {

    private static final Logger log = LoggerFactory.getLogger(InsiderHomePageSteps.class);

    /**
     * Each thread to get its own instance.
     */
    private final InsiderHomePage insiderHomePage = new InsiderHomePage();
    private final CommonPage commonPage = new CommonPage();


    @Given("Navigate : to {string}")
    @Step("Given I navigate to '{url}'")
    public void iNavigateTo(String url) {
        log.info("Step: I navigate to '{}'", url);
        String navUrl = url.isEmpty() ? ConfigManager.getBaseUrl() : url;
        insiderHomePage.open(navUrl);
    }

    @Then("Verify : the Insider home page should be opened")
    @Step("Then the Insider home page should be opened")
    public void theInsiderHomePageShouldBeOpened() {
        log.info("Step: Verifying Insider home page is opened");

        boolean isLoaded = commonPage.isPageLoaded("Home Page");
        String currentUrl = commonPage.getUrl();
        String title = commonPage.getTitle();

        log.info("Assert homepage loaded → URL: '{}', Title: '{}'", currentUrl, title);
        assertTrue(isLoaded,  "Home page did not load correctly. URL: " + currentUrl + " | Title: " + title);
    }

    @And("Verify : the main blocks on the homepage should be visible")
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

        log.info("All homepage blocks verified successfully.");
    }

    @When("Check : {string} cookies")
    @Step("When check {string} cookies")
    public void checkCookies(String approveOrDecline) {
        log.info("Step: Check cookie popup as {}", approveOrDecline);
        insiderHomePage.checkCookiesIfPresent(approveOrDecline);
    }
}
