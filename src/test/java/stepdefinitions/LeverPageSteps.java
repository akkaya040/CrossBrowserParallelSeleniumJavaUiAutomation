package stepdefinitions;

import framework.pages.CommonPage;
import framework.pages.InsiderCareerPage;
import framework.pages.LeverPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.assertTrue;

public class LeverPageSteps {

    private static final Logger log = LoggerFactory.getLogger(LeverPageSteps.class);

    /**
     * Each thread to get its own instance.
     */
    private final LeverPage leverPage = new LeverPage();

    @Then("Verify : lever jobs page should be opened")
    @Step("Then Verify : lever jobs page should be opened")
    public void verifyLeverPageShouldBeOpened() {
        log.info("Step: Verifying Insider careers page is opened");

        boolean isLoaded = leverPage.isLeverPageLoaded();
        assertTrue(isLoaded,  "Lever page did not load correctly.");
    }

    @And("Select : lever job filters")
    @Step("Select : lever job filters")
    public void selectLeverJobFilters(DataTable dataTable) {
        leverPage.selectLeverJobFilters(dataTable);
    }

    @Then("Verify : lever filtered jobs contains {string}")
    @Step("Then Verify : lever filtered jobs contains {expectedData}")
    public void verifyLeverFilteredJobsContains(String expectedData) {
        leverPage.verifyLeverFilteredJobsContains(expectedData);
    }

    @And("Click : apply first first job")
    @Step("And Click : apply first first job")
    public void clickApplyFirstFirstJob() {
        leverPage.clickApplyFirstFirstJob();
    }

    @Then("Verify : page forwarded to the application page")
    @Step("Then Verify : page forwarded to the application page")
    public void verifyPageForwardedToTheApplicationPage() {
        leverPage.verifyPageForwardedToTheApplicationPage();
    }
}
