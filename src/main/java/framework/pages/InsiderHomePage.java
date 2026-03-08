package framework.pages;

import framework.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

import static framework.pages.constants.InsiderHomePageConstants.*;

public class InsiderHomePage extends BasePage {


    @Step("Navigate to '{url}'")
    public void open(String url) {
        log.info("Insider ana sayfası açılıyor: {}", url);
        navigateTo(url);
        acceptCookiesIfPresent();
    }

    @Step("Accept cookie consent if present")
    public void acceptCookiesIfPresent() {
        try {
            WaitUtils.customWait(4)
                    .until(d -> {
                        List<WebElement> buttons = d.findElements(COOKIE_ACCEPT_BUTTON);
                        return buttons.stream()
                                .filter(WebElement::isDisplayed)
                                .findFirst()
                                .map(btn -> {
                                    btn.click();
                                    return true;
                                })
                                .orElse(false);
                    });
            log.info("Cookie banner kapatıldı");
        } catch (Exception e) {
            log.debug("Cookie banner bulunamadı veya zaten kapalı");
        }
    }

    @Step("Check homepage loaded successfully")
    public boolean isHomePageLoaded() {
        try {
            findElement(BODY);
            String currentUrl = getCurrentUrl();
            String title = getPageTitle();
            log.info("Sayfa yüklendi | URL: {} | Title: {}", currentUrl, title);
            return currentUrl.contains("insider") || title.toLowerCase().contains("insider");
        } catch (Exception e) {
            log.error("Ana sayfa yükleme kontrolü başarısız: {}", e.getMessage());
            return false;
        }
    }

    @Step("Check navigation bar is visible")
    public boolean isNavigationBarVisible() {
        try {
            WebElement navBar = findElement(NAVIGATION_BAR);
            WaitUtils.waitForVisibility(navBar);
            return navBar.isDisplayed();
        } catch (Exception e) {
            log.debug("Navigasyon çubuğu görünmüyor: {}", e.getMessage());
            return false;
        }
    }

    @Step("Check hero section is visible")
    public boolean isHeroSectionVisible() {
        try {
            WebElement hero = findElement(HERO_SECTION);
            WaitUtils.waitForVisibility(hero);
            return hero.isDisplayed();
        } catch (Exception e) {
            log.debug("Hero bölümü görünmüyor: {}", e.getMessage());
            return false;
        }
    }

    @Step("Get count of navigation links")
    public int getNavigationLinksCount() {
        try {
            List<WebElement> links = findElements(NAVIGATION_LINKS);
            long visibleCount = links.stream().filter(this::isDisplayed).count();
            log.debug("Görünür navigasyon link sayısı: {}", visibleCount);
            return (int) visibleCount;
        } catch (Exception e) {
            log.debug("Navigasyon linkleri sayılamadı: {}", e.getMessage());
            return 0;
        }
    }

    @Step("Check footer is visible")
    public boolean isFooterVisible() {
        try {
            scrollToBottom();
            WebElement footer = findElement(FOOTER);
            WaitUtils.waitForVisibility(footer);
            return footer.isDisplayed();
        } catch (Exception e) {
            log.debug("Footer görünmüyor: {}", e.getMessage());
            return false;
        }
    }

    @Step("Check partner/customer section is visible")
    public boolean isPartnerSectionVisible() {
        List<WebElement> sections = findElements(PARTNER_SECTIONS);
        return sections.stream().anyMatch(this::isDisplayed);
    }

    @Step("Check CTA buttons are visible")
    public boolean hasVisibleCTAButtons() {
        List<WebElement> buttons = findElements(CTA_BUTTONS);
        return buttons.stream().anyMatch(this::isDisplayed);
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
