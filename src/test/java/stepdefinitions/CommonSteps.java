package stepdefinitions;

import framework.pages.CommonPage;
import io.cucumber.java.en.And;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static framework.utils.Assertions.*;

public class CommonSteps {

    private static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    /**
     * Each thread to get its own instance.
     */
    private final CommonPage commonPage = new CommonPage();

    @And("wait for {int} seconds")
    @Step("wait for {int} seconds")
    public void waitForSeconds(int second) {
        commonPage.waitForSeconds(second);
    }

    @And("fail test on this step")
    @Step("Verify to get screenshot on fail.")
    public void failTestOnThisStep() {
        assertFail("Verify to get screenshot on fail.");
        log.info("Verify to get screenshot on fail.");
    }

}
