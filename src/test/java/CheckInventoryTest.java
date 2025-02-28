import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.inventory.InventoryPageObject;
import pages.login.LoginPageObject;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CheckInventoryTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private InventoryPageObject objInventoryPage;
    private LoginPageObject objLoginPage;

    @BeforeClass
    @Parameters({"username", "password"})
    public void setup(String username, String password) {
        String url = "https://www.saucedemo.com/";
        driver = new ChromeDriver();
        objLoginPage = new LoginPageObject(driver);
        objInventoryPage = new InventoryPageObject(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(username, password);
    }

    @Test(priority = -1)
    public void checkSorting() {
        // Wait for the dropdown to be clickable
        WebElement sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getSortingDropdown()));
        sortingDropdown.click();

        // Wait for the second option to be clickable
        WebElement secondOption = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getDescendingOrderOption()));
        secondOption.click();

        WebElement firstItem = wait.until(ExpectedConditions.visibilityOf(objInventoryPage.getInventoryItemNameFirst()));
        String textOfTheFirstItem = firstItem.getText();
        Assert.assertNotEquals(textOfTheFirstItem, "\"^[^A].*\"");

        sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getSortingDropdown()));
        sortingDropdown.click();
        WebElement thirdOption = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getPricesAscendingOrderOption()));
        thirdOption.click();

        WebElement firstItemPrice = wait.until(ExpectedConditions.visibilityOf(objInventoryPage.getInventoryItemPriceFirst()));
        String textOfTheFirstItemPrice = firstItemPrice.getText();
        Assert.assertNotEquals(textOfTheFirstItemPrice, "^\\d{1,2}\\.\\d{2}$");
    }

    @Test(priority = 1)
    public void addToCartCheck() {
        List<WebElement> addToCartElements = wait.until(ExpectedConditions.visibilityOfAllElements(objInventoryPage.getAddToCartButton()));
        addToCartElements.get(0).click();
        addToCartElements.get(1).click();
        WebElement shoppingCart = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getShoppingCartBadge()));
        String shoppingCartNumber = shoppingCart.getText();
        Assert.assertEquals(shoppingCartNumber, "2");
    }

    @Test (priority = 2)
    public void removeFromCartCheck() {
        WebElement shoppingCart = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getShoppingCartBadge()));
        shoppingCart.click();
        List<WebElement> removeFromCartElements = wait.until(ExpectedConditions.visibilityOfAllElements(objInventoryPage.getRemoveFromCartButton()));
        Assert.assertTrue(removeFromCartElements.size() >= 2, "Not enough 'Remove from Cart' buttons found!");
        removeFromCartElements.getFirst().click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//button[contains(@id, 'remove')]"), 0));
        removeFromCartElements.getFirst().click();
        boolean isCartBadgeInvisible = wait.until(ExpectedConditions.invisibilityOf(objInventoryPage.getShoppingCartBadge()));
        Assert.assertTrue(isCartBadgeInvisible, "Shopping cart badge is still visible, but it should be removed.");
    }

    @Test(priority = 3)
    public void openFooterLinks() {
        Set<String> oldWindows = driver.getWindowHandles();

        WebElement linkInFooter = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.getFirstLinkFooter()));
        linkInFooter.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));

        Set<String> newWindows = driver.getWindowHandles();
        newWindows.removeAll(oldWindows); // Find the new tab's handle

        Assert.assertEquals(newWindows.size(), 1, "A new tab should have been opened!");
        String newTabHandle = newWindows.iterator().next();

        driver.switchTo().window(newTabHandle);
        Assert.assertEquals(driver.getCurrentUrl(), "https://x.com/saucelabs");
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
