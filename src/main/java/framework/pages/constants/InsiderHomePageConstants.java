package framework.pages.constants;

import org.openqa.selenium.By;

public class InsiderHomePageConstants {

    public static final By BODY = By.tagName("body");
    public static final By NAVIGATION_BAR = By.cssSelector("nav#navigation, nav.navigation, #nav-header, header nav");
    public static final By NAVIGATION_LINKS = By.cssSelector("nav a.nav-link, nav ul li a, #navigation a");
    public static final By LOGO = By.xpath("//div[@class='header-logo']");
    public static final By HERO_SECTION = By.cssSelector("[class*='hero'], section:first-of-type");
    public static final By FOOTER = By.cssSelector("footer, .footer, #footer");
    public static final By PARTNER_SECTIONS = By.cssSelector(".homepage-case-study-body-item-content-end");
    public static final By CTA_BUTTONS = By.cssSelector("a.btn, button.btn, .cta-button, [class*='cta']");
    public static final By COOKIE_ACCEPT_BUTTON = By.cssSelector("div a#wt-cli-accept-all-btn");
    public static final By COOKIE_DECLINE_BUTTON = By.cssSelector("div a#wt-cli-reject-btn");

}
