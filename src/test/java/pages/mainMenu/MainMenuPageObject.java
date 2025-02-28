package pages.mainMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainMenuPageObject {
    WebDriver driver;

    // Using @FindBy annotations to locate elements
    @FindBy(className = "bm-burger-button")
    public WebElement menuButton;

    @FindBy(className = "bm-cross-button")
    public WebElement closeMenuButton;

    // Constructor to initialize the PageFactory elements
    public MainMenuPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements
    }

    // Method to open a specific menu item
    public void openMenuItem(int menuItem) {
        String itemSelector = ".bm-item-list > a:nth-child(" + menuItem + ")";
        By menuItemSelector = By.cssSelector(itemSelector);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(menuButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuItemSelector)).click();
    }

    // Method to close the menu
    public void closeMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closeMenuButton)).click();
    }
}
