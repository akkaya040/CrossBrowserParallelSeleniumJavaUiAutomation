package stepdefinitions;

import framework.config.ConfigManager;
import framework.pages.CommonPage;
import framework.pages.InsiderCareerPage;
import framework.pages.InsiderHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.assertGreaterThanZero;
import static framework.utils.Assertions.assertTrue;

public class InsiderCareerPageSteps {

    private static final Logger log = LoggerFactory.getLogger(InsiderCareerPageSteps.class);

    /**
     * Each thread to get its own instance.
     */
    private final InsiderCareerPage insiderCareerPage = new InsiderCareerPage();
    private final CommonPage commonPage = new CommonPage();

    @Then("Verify : the Insider careers page should be opened")
    @Step("Then Verify : the Insider careers page should be opened'")
    public void verifyTheInsiderCareersPageShouldBeOpened() {
        log.info("Step: Verifying Insider careers page is opened");
        boolean isLoaded = commonPage.isPageLoaded("Career Page");
        String currentUrl = commonPage.getUrl();
        String title = commonPage.getTitle();
        log.info("Assert homepage loaded → URL: '{}', Title: '{}'", currentUrl, title);
        assertTrue(isLoaded,  "Career page did not load correctly. URL: " + currentUrl + " | Title: " + title);
        assertTrue(currentUrl.contains("career"),"Career page title doesn't contain career!");
    }

    @And("Click : explore open roles button")
    @Step("And Click : explore open roles button'")
    public void clickExploreOpenRolesButton() {
        insiderCareerPage.clickExploreOpenRolesButton();
    }

    @Then("Verify : open role cards")
    @Step("Then Verify : open role cards")
    public void verifyOpenRoleCards() {
        insiderCareerPage.verifyOpenRoleCards();
    }

    @When("^Click : open positions on the role \"(Quality Assurance|Software Development|Sales)\"$")
    @Step("Click : open positions on the role '{department}'")
    public void clickOpenPositionsOnTheRole(String department) {
        insiderCareerPage.clickOpenPositionsOnTheRole(department);
    }
}
