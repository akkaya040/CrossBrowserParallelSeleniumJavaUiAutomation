package framework.pages;

import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static framework.pages.constants.InsiderHomePageConstants.BODY;
import static framework.pages.constants.LeverPageConstants.*;
import static framework.utils.Assertions.*;
import static framework.utils.WaitUtils.waitForAllVisible;

public class LeverPage extends BasePage {

    public final String LocationType = "Location Type";
    public final String Location = "Location";
    public final String Team = "Team";
    public final String WorkType = "Work Type";

    @Step("Check career page loaded successfully")
    public boolean isLeverPageLoaded() {

        waitForUrlContains("lever");
        waitUntilPageLoad();
        try {
            findElement(BODY);
            String currentUrl = getCurrentUrl();
            String title = getPageTitle();
            log.info("Page loaded | URL: {} | Title: {}", currentUrl, title);
            return currentUrl.contains("lever") && title.toLowerCase().contains("insider");
        } catch (Exception e) {
            log.error("Lever page loading check is  unsuccessful: {}", e.getMessage());
            return false;
        }
    }

    public void selectLeverJobFilters(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> map : list) {
            String locationTypeValue = map.get(LocationType);
            selectFilterAndValue(LocationType, locationTypeValue);
            String locationValue = map.get(Location);
            selectFilterAndValue(Location, locationValue);
            String teamValue = map.get(Team);
            selectFilterAndValue(Team, teamValue);
            String workTypeValue = map.get(WorkType);
            selectFilterAndValue(WorkType, workTypeValue);
        }
    }

    private void selectFilterAndValue(String filter, String filterValue) {
        if (!isNullOrEmpty(filterValue)) {
            By loc = getFilterLocator(filter);
            click(loc);
            pause(500);
            By valueLoc = By.xpath("//a[contains(text(),'" + filterValue + "')]");
            jsClickWithQuery(valueLoc);
            pause(1000);
        }
    }

    private By getFilterLocator(String filter) {
        return switch (filter) {
            case LocationType -> LOCATION_TYPE_FILTER;
            case Location -> LOCATION_FILTER;
            case Team -> TEAM_FILTER;
            case WorkType -> WORK_TYPE_FILTER;
            default -> {
                assertFail("Unknown filter: " + filter);
                yield null;
            }
        };
    }

    public void verifyLeverFilteredJobsContains(String expectedData) {

        List<WebElement> jobs = waitForAllVisible(LISTED_POSITIONS);
        for (WebElement job : jobs) {
            String jobText = job.getText().toLowerCase();
            assertTrue(jobText.contains(expectedData.toLowerCase()),
                    "Listed jobs doesn't contain filtered conditions-> Expected: " + expectedData +
                            "Actual: " + jobText);
        }
    }

    public void clickApplyFirstFirstJob() {
        click(findElements(APPLY_BTN).get(0));
        log.info("First listed job clicked.");
    }

    public void verifyPageForwardedToTheApplicationPage() {
        String pageTitle = getCurrentUrl();
        assertContains(pageTitle, "https://jobs.lever.co/insiderone", "Page forward is not working properly!");
        log.info("Page is forwarded successfully.");
    }
}
