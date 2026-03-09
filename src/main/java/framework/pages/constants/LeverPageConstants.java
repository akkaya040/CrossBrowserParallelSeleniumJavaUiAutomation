package framework.pages.constants;

import org.openqa.selenium.By;

public class LeverPageConstants {

    public static final By LEVER_MAIN_HEADER = By.xpath("//a[@href='https://jobs.lever.co/insiderone']");
    public static final By LOCATION_TYPE_FILTER = By.xpath("//div[contains(@aria-label,'Filter by Location type')]");
    public static final By LOCATION_FILTER = By.xpath("//div[contains(@aria-label,'Filter by Location:')]");
    public static final By TEAM_FILTER = By.xpath("//div[contains(@aria-label,'Filter by Team:')]");
    public static final By WORK_TYPE_FILTER = By.xpath("//div[contains(@aria-label,'Filter by Work type:')]");
    public static final By LISTED_POSITIONS = By.cssSelector("a.posting-title");
    public static final By APPLY_BTN = By.cssSelector("[data-qa='btn-apply']");

}
