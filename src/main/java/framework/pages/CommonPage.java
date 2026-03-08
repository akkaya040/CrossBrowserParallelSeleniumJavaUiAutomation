package framework.pages;

import io.qameta.allure.Step;

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
}
