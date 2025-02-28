import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.login.LoginPageObject;
import pages.mainMenu.MainMenuPageObject;

import java.time.Duration;

public class CheckMainMenuTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainMenuPageObject objMainMenu;
    private LoginPageObject objLoginPage;

    @BeforeClass
    @Parameters({"username", "password"})
    public void setup(String username, String password) {
        String url = "https://www.saucedemo.com/";
        driver = new ChromeDriver();
        objLoginPage = new LoginPageObject(driver);
        objMainMenu = new MainMenuPageObject(driver); // PageFactory will initialize elements
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(username, password);
    }

    @Test
    public void openAboutPage() {
        objMainMenu.openMenuItem(2); // Open the About page
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test
    public void openCloseMenu() {
        driver.navigate().back();
        wait.until(ExpectedConditions.elementToBeClickable(objMainMenu.menuButton));
        objMainMenu.menuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(objMainMenu.closeMenuButton));
        objMainMenu.closeMenu();
        boolean closeMainMenuIcon = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("bm-cross-button")));
        Assert.assertTrue(closeMainMenuIcon, "Menu is not closed");
    }

    @Test
    public void selectLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(objMainMenu.menuButton));
        objMainMenu.openMenuItem(3); // Open the Logout option
        Assert.assertTrue((objLoginPage.login).isDisplayed()); // Check if login page is displayed
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
