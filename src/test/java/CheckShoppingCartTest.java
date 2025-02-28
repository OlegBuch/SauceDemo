import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pages.inventory.InventoryPageObject;
import pages.login.LoginPageObject;
import pages.shoppingCart.ShoppingCartPageObject;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;


public class CheckShoppingCartTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private ShoppingCartPageObject objShoppingCartPage;
    private InventoryPageObject objInventoryPage;
    private LoginPageObject objLoginPage;
    private final String firstName = "John";
    private final String lastName = "Dorry";
    private final String postalCode = "1432222";

    @BeforeClass
    @Parameters({"username", "password"})
    public void setup(String username, String password) {
        String url = "https://www.saucedemo.com/";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize Page Objects using PageFactory
        objLoginPage = PageFactory.initElements(driver, LoginPageObject.class);
        objInventoryPage = PageFactory.initElements(driver, InventoryPageObject.class);
        objShoppingCartPage = PageFactory.initElements(driver, ShoppingCartPageObject.class);

        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(username, password);
    }

    @Test(priority = -1)
    public void openShoppingCartPage() {
        objInventoryPage.addItemsToShoppingCart();
        wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.shoppingCartBadge)).click();
        wait.until(ExpectedConditions.elementToBeClickable(objInventoryPage.inventoryItemName));

        List<WebElement> shoppingCartNames = objInventoryPage.getShoppingCartNames();
        List<WebElement> shoppingCartDescriptions = objInventoryPage.getShoppingCartDescriptions();
        List<WebElement> shoppingCartPrices = objInventoryPage.getShoppingCartPrices();
        List<WebElement> shoppingCartRemoveButtons = objInventoryPage.getShoppingCartRemoveButtons();

        assertEquals(shoppingCartNames.size(), 2);
        assertEquals(shoppingCartDescriptions.size(), 2);
        assertEquals(shoppingCartPrices.size(), 2);
        assertEquals(shoppingCartRemoveButtons.size(), 2);
    }

    @Test(priority = 1)
    public void checkContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(objShoppingCartPage.getContinueShoppingButton())).click();

        assertTrue(objLoginPage.getShoppingCartText().isDisplayed());
        assertEquals(objLoginPage.getProductText(), "Products");
    }

    @Test(priority = 2)
    public void removeItemsFromShoppingCart() {
        wait.until(ExpectedConditions.visibilityOf(objInventoryPage.shoppingCartBadge)).click();
        objInventoryPage.removeItemsFromShoppingCart();
        boolean itemsRemoved = wait.until(ExpectedConditions.invisibilityOf(objShoppingCartPage.getAddToCartButton()));
        assertTrue(itemsRemoved, "Items were not removed from the shopping cart");
    }

    @Test(priority = 3)
    public void checkoutFromShoppingCart() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        objInventoryPage.addItemsToShoppingCart();
        wait.until(ExpectedConditions.visibilityOf(objInventoryPage.shoppingCartBadge)).click();
        wait.until(ExpectedConditions.elementToBeClickable(objShoppingCartPage.getCheckoutButton())).click();

        wait.until(ExpectedConditions.visibilityOf(objShoppingCartPage.getFirstNameInput()));
        objShoppingCartPage.enterCustomerInformation(firstName, lastName, postalCode);

        List<WebElement> shoppingCartNames = objInventoryPage.getShoppingCartNames();
        List<WebElement> shoppingCartDescriptions = objInventoryPage.getShoppingCartDescriptions();
        List<WebElement> shoppingCartPrices = objInventoryPage.getShoppingCartPrices();

        assertEquals(shoppingCartNames.size(), 2);
        assertEquals(shoppingCartDescriptions.size(), 2);
        assertEquals(shoppingCartPrices.size(), 2);

        assertTrue(objShoppingCartPage.isPaymentInfoDisplayed());
        assertTrue(objShoppingCartPage.isShippingInfoDisplayed());
        assertTrue(objShoppingCartPage.isSubTotalDisplayed());
        assertTrue(objShoppingCartPage.isTaxDisplayed());
        assertTrue(objShoppingCartPage.isTotalDisplayed());

        objShoppingCartPage.clickFinishCheckout();
        wait.until(ExpectedConditions.elementToBeClickable(objShoppingCartPage.getBackHomeButton())).click();

        assertTrue(objLoginPage.getShoppingCartText().isDisplayed());
        assertEquals(objLoginPage.getProductText(), "Products");
    }

    @Test(priority = 4)
    public void cancelCheckoutFlow() {
        objInventoryPage.addItemsToShoppingCart();
        wait.until(ExpectedConditions.visibilityOf(objInventoryPage.shoppingCartBadge)).click();
        wait.until(ExpectedConditions.elementToBeClickable(objShoppingCartPage.getCheckoutButton())).click();
        wait.until(ExpectedConditions.visibilityOf(objShoppingCartPage.getFirstNameInput()));
        objShoppingCartPage.enterCustomerInformation(firstName, lastName, postalCode);
        wait.until(ExpectedConditions.elementToBeClickable(objShoppingCartPage.getCancelCheckoutButton())).click();

        assertTrue(objLoginPage.getShoppingCartText().isDisplayed());
        assertEquals(objLoginPage.getProductText(), "Products");
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}


