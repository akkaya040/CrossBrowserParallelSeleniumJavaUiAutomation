package framework.pages;

import io.qameta.allure.Step;

import static framework.pages.constants.InsiderHomePageConstants.BODY;

public class CommonPage extends BasePage {


    public void waitForSeconds(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (Exception e) {
            log.error("Waiting Thread Catch Exception: {}", e.getMessage());
        }
    }


    @Step("Get page title")
    public String getTitle() {
        return getPageTitle();
    }

    @Step("Get current URL")
    public String getUrl() {
        return getCurrentUrl();
    }

    @Step("Verify '{pageName}' is loaded ")
    public boolean isPageLoaded(String pageName) {
        try {
            findElement(BODY);
            String currentUrl = getCurrentUrl();
            String title = getPageTitle();
            log.info("Page loaded | URL: {} | Title: {}", currentUrl, title);
            return currentUrl.contains("insider") || title.toLowerCase().contains("insider");
        } catch (Exception e) {
            log.error("{} loading check is  unsuccessful: {}", pageName, e.getMessage());
            return false;
        }
    }

}
