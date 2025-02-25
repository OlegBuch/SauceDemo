package pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class InventoryPageObject {
    WebDriver driver;
    public By sortingDropdown = By.className("product_sort_container");
    public By descendingOrderOption = By.cssSelector("select option:nth-of-type(2)");
    public By pricesAscendingOrderOption = By.cssSelector("select option:nth-of-type(3)");
    public By inventoryItemNameFirst = By.cssSelector(".inventory_item_name:first-of-type");
    public By inventoryItemPriceFirst = By.cssSelector(".inventory_item_price:first-of-type");
    public By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory:nth-of-type(1)");
    public By removeFromCartButton = By.xpath("//*[contains(text(), 'Remove')]");
    public By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");
    public By firstLinkFooter = By.cssSelector("[data-test=\"social-twitter\"]");



    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
