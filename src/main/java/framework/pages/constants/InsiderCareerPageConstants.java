package framework.pages.constants;

import org.openqa.selenium.By;

public class InsiderCareerPageConstants {

    public static final By OPEN_ROLES_BUTTON = By.cssSelector(".insiderone-hero-banner-content-buttons");
    public static final By OPEN_ALL_TEAMS_BUTTON = By.cssSelector(".inso-btn.see-more");
    public static final By OPEN_POSITONS_BUTTON = By.cssSelector(".insiderone-icon-cards-grid-item-btn");

    public static By ROLE_OPEN_POSITONS_BUTTON(String department) {
        return By.xpath("//div[@data-department='" + department + "']//a[@class='insiderone-icon-cards-grid-item-btn']");
    }

}
