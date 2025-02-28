package pages.shoppingCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPageObject {
    WebDriver driver;

    /*@FindBy(className = "bm-burger-button")
    public WebElement menuButton;

    @FindBy(className = "bm-cross-button")
    public WebElement closeMenuButton;
    */
    @FindBy(css = "[data-test=\"continue-shopping\"]")
    public WebElement continueShoppingButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButtonShoppingCart;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "first-name")
    public WebElement firstNameInput;

    @FindBy(id = "last-name")
    public WebElement lastNameInput;

    @FindBy(id = "postal-code")
    public WebElement postalCodeInput;

    @FindBy(id = "continue")
    public WebElement continueButtonCheckoutPage;

    @FindBy(css = "[data-test=\"payment-info-value\"]")
    public WebElement paymentInfoValue;

    @FindBy(css = "[data-test=\"shipping-info-value\"]")
    public WebElement shippingInformationValue;

    @FindBy(css = "[data-test=\"subtotal-label\"]")
    public WebElement subTotalValue;

    @FindBy(css = "[data-test=\"tax-label\"]")
    public WebElement taxTotalValue;

    @FindBy(css = "[data-test=\"total-label\"]")
    public WebElement totalValue;

    @FindBy(id = "finish")
    public WebElement finishCheckButton;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    @FindBy(id = "cancel")
    public WebElement cancelCheckoutButton;

    // Constructor: Initializes elements with PageFactory
    public ShoppingCartPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCustomerInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        continueButtonCheckoutPage.click();
    }

    /*public void clickCheckout() {
        checkoutButton.click();
    }*/

    public void clickFinishCheckout() {
        finishCheckButton.click();
    }
/*

    public void clickBackToProducts() {
        backHomeButton.click();
    }
*/
/*

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public void clickCancelCheckout() {
        cancelCheckoutButton.click();
    }
*/

    public boolean isPaymentInfoDisplayed() {
        return paymentInfoValue.isDisplayed();
    }

    public boolean isShippingInfoDisplayed() {
        return shippingInformationValue.isDisplayed();
    }

    public boolean isSubTotalDisplayed() {
        return subTotalValue.isDisplayed();
    }

    public boolean isTaxDisplayed() {
        return taxTotalValue.isDisplayed();
    }

    public boolean isTotalDisplayed() {
        return totalValue.isDisplayed();
    }

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    public WebElement getCheckoutButton() {
        return checkoutButton;
    }

    public WebElement getFirstNameInput() {
        return firstNameInput;
    }

    public WebElement getCancelCheckoutButton() {
        return cancelCheckoutButton;
    }

    public WebElement getBackHomeButton() {
        return backHomeButton;
    }

    public WebElement getAddToCartButton() {
        return addToCartButtonShoppingCart;
    }
}
