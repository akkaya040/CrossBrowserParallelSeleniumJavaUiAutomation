package stepdefinitions;

import framework.pages.CommonPage;
import framework.pages.InsiderHomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.*;

public class CommonSteps {

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    /** Her senaryo thread'i kendi page instance'ını alır. */
    private final CommonPage commonPage = new CommonPage();


    @And("wait for {int} seconds")
    @Step("wait for {int} seconds")
    public void waitForSeconds(int second) {
        commonPage.waitForSeconds(second);
    }

}
