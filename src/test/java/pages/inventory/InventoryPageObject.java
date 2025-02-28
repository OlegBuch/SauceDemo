package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPageObject {
    WebDriver driver;

    // Elements
    @FindBy(className = "product_sort_container")
    public WebElement sortingDropdown;

    @FindBy(css = "select option:nth-of-type(2)")
    public WebElement descendingOrderOption;

    @FindBy(css = "select option:nth-of-type(3)")
    public WebElement pricesAscendingOrderOption;

    @FindBy(css = ".inventory_item_name:first-of-type")
    public WebElement inventoryItemNameFirst;

    @FindBy(css = ".inventory_item_price:first-of-type")
    public WebElement inventoryItemPriceFirst;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButton;

    @FindBy(xpath = "//button[contains(@id, 'remove')]")
    public List<WebElement> removeFromCartButton;

    @FindBy(css = ".shopping_cart_badge")
    public WebElement shoppingCartBadge;

    @FindBy(css = "[data-test=\"social-twitter\"]")
    public WebElement firstLinkFooter;

    @FindBy(className = "inventory_item_name")
    public WebElement inventoryItemName;

    @FindBy(css = ".cart_item .inventory_item_name")
    public List<WebElement> shoppingCartNames;

    @FindBy(css = ".cart_item .inventory_item_desc")
    public List<WebElement> shoppingCartDescriptions;

    @FindBy(css = ".cart_item .inventory_item_price")
    public List<WebElement> shoppingCartPrices;

    @FindBy(css = ".cart_item .btn_secondary")
    public List<WebElement> shoppingCartRemoveButtons;

    // Constructor: Initializes elements with PageFactory
    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with elements

    public void addItemsToShoppingCart() {
        List<WebElement> addToCartElements = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        addToCartElements.get(0).click();
        addToCartElements.get(1).click();
    }

    public void removeItemsFromShoppingCart() {
        List<WebElement> removeFromCartButtons = driver.findElements(By.xpath("//*[contains(text(), 'Remove')]"));
        removeFromCartButtons.get(0).click();
        removeFromCartButtons.get(1).click();
    }

    // Getter methods for interacting in test cases
    public WebElement getSortingDropdown() {
        return sortingDropdown;
    }

    public WebElement getDescendingOrderOption() {
        return descendingOrderOption;
    }

    public WebElement getPricesAscendingOrderOption() {
        return pricesAscendingOrderOption;
    }

    public WebElement getInventoryItemNameFirst() {
        return inventoryItemNameFirst;
    }

    public WebElement getInventoryItemPriceFirst() {
        return inventoryItemPriceFirst;
    }

    public List<WebElement> getAddToCartButton() {
        return addToCartButton;
    }

    public List<WebElement> getRemoveFromCartButton() {
        return removeFromCartButton;
    }

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public WebElement getFirstLinkFooter() {
        return firstLinkFooter;
    }

    public List<WebElement> getShoppingCartNames() {
        return shoppingCartNames;
    }


    public List<WebElement> getShoppingCartDescriptions() {
        return shoppingCartDescriptions;
    }

    public List<WebElement> getShoppingCartPrices() {
        return shoppingCartPrices;
    }

    public List<WebElement> getShoppingCartRemoveButtons() {
        return shoppingCartRemoveButtons;
    }
}
