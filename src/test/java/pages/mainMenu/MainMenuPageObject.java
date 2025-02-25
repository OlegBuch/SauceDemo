package pages.mainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainMenuPageObject {
    WebDriver driver;
    public By menuButton = By.className("bm-burger-button");
    public By closeMenuButton = By.className("bm-cross-button");


    public MainMenuPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenuItem(WebDriver driver, int menuItem) {
        String itemSelector = ".bm-item-list > a:nth-child(" + menuItem + ")";
        By menuItemSelector = By.cssSelector(itemSelector);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuAboutPage = wait.until(ExpectedConditions.visibilityOfElementLocated(menuButton));
        menuAboutPage.click();
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(menuItemSelector));
        item.click();
    }
}
