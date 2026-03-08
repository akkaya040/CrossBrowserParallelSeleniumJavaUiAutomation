package framework.pages.constants;

import org.openqa.selenium.By;

public class InsiderHomePageConstants {

    public static final By BODY = By.tagName("body");
    public static final By NAVIGATION_BAR = By.cssSelector("nav#navigation, nav.navigation, #nav-header, header nav");
    public static final By NAVIGATION_LINKS = By.cssSelector("nav a.nav-link, nav ul li a, #navigation a");
    public static final By LOGO = By.cssSelector("a.navbar-brand img, header img[alt*='Insider'], img[src*='insider-logo']");
    public static final By HERO_SECTION = By.cssSelector(
            "section.home-hero, section.main-hero, .hero-section, " +
                    "[class*='hero'], [data-testid='hero'], section:first-of-type");
    public static final By FOOTER = By.cssSelector("footer, .footer, #footer");
    public static final By PARTNER_SECTIONS = By.cssSelector(
            "[class*='partner'], [class*='customer'], [class*='client'], " +
                    "[class*='logo-section'], [class*='trusted']");
    /**
     * CTA (eylem çağrısı) butonları.
     */
    public static final By CTA_BUTTONS = By.cssSelector("a.btn, button.btn, .cta-button, [class*='cta']");
    public static final By COOKIE_ACCEPT_BUTTON = By
            .cssSelector("#wt-cli-accept-all-btn, button[id*='accept'], .cli-plugin-main-button");
}
