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
import pages.inventory.InventoryPagePo;
import pages.login.LoginPagePo;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CheckInventoryPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private InventoryPagePo objInventoryPage;
    private LoginPagePo objLoginPage;

    @BeforeClass
    @Parameters({"username", "password"})
    public void setup(String username, String password) {
        String url = "https://www.saucedemo.com/";
        driver = new ChromeDriver();
        objLoginPage = new LoginPagePo(driver);
        objInventoryPage = new InventoryPagePo(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(username, password);
    }

    @Test
    public void checkSorting() {

        // Wait for the dropdown to be clickable
        WebElement sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.sortingDropdown));
        sortingDropdown.click();

        // Wait for the second option to be clickable
        WebElement secondOption = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.descendingOrderOption));
        secondOption.click();

        WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(objInventoryPage.inventoryItemNameFirst));
        String textOfTheFirstItem = firstItem.getText();
        Assert.assertNotEquals(textOfTheFirstItem, "\"^[^A].*\"");

        sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.sortingDropdown));
        sortingDropdown.click();
        WebElement thirdOption = wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.pricesAscendingOrderOption));
        thirdOption.click();

        WebElement firstItemPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(objInventoryPage.inventoryItemPriceFirst));
        String textOfTheFirstItemPrice = firstItemPrice.getText();
        Assert.assertNotEquals(textOfTheFirstItemPrice, "^\\d{1,2}\\.\\d{2}$");
    }

    @Test
    public void addToCartCheck(){
        List<WebElement> addToCartElements = driver.findElements(objInventoryPage.addToCartButton);
        addToCartElements.get(0).click();
        addToCartElements.get(1).click();
        WebElement shoppingCart = wait.until(ExpectedConditions.visibilityOfElementLocated(objInventoryPage.shoppingCartBadge));
        String shoppingCartNumber = shoppingCart.getText();
        Assert.assertEquals(shoppingCartNumber, "2");
    }

    @Test
    public void openFooterLinks(){
        Set<String> oldWindows = driver.getWindowHandles();

        WebElement linkInFooter = wait.until(ExpectedConditions.visibilityOfElementLocated(objInventoryPage.firstLinkFooter));
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
    public void closeBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }
}
