package framework.pages;

import org.openqa.selenium.WebElement;

import static framework.pages.constants.InsiderCareerPageConstants.*;
import static framework.utils.Assertions.assertTrue;
import static framework.utils.WaitUtils.waitForPresence;

public class InsiderCareerPage extends BasePage {

    public void clickExploreOpenRolesButton() {
        click(OPEN_ROLES_BUTTON);
        log.info("Explore open roles button is clicked");

        waitForPresence(OPEN_ALL_TEAMS_BUTTON);
        WebElement allTeamsBtn = findElement(OPEN_ALL_TEAMS_BUTTON);

        jsClick(allTeamsBtn);
        log.info("See all teams button is clicked");
    }

    public void clickOpenPositionsOnTheRole(String department) {
        scrollToElement(ROLE_OPEN_POSITONS_BUTTON(department));
        hoverElement(ROLE_OPEN_POSITONS_BUTTON(department));
        pause(5000);
        click(ROLE_OPEN_POSITONS_BUTTON(department));
        log.info("Clicked open positions button on the role of {} department", department);
    }

    public void verifyOpenRoleCards() {
        int cardCount = findElements(OPEN_POSITONS_BUTTON).size();
        assertTrue(cardCount > 6, "Card count after expanding must be greater then 6");
    }
}
