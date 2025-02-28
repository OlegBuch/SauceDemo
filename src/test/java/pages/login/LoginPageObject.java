package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageObject {
    WebDriver driver;

    @FindBy(id = "user-name")
    public WebElement login;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(css = "#login-button")
    public WebElement loginButton;

    @FindBy(css = "[data-test=\"title\"]")
    public WebElement productText;

    @FindBy(css = "[data-test=\"shopping-cart-link\"]")
    public WebElement shoppingCart;

    @FindBy(css = "[data-test=\"error\"]")
    public WebElement errorText;

    // Constructor to initialize elements
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCredentialsAndSubmit(String username, String passwordValue) {
        login.sendKeys(username);
        password.sendKeys(passwordValue);
        loginButton.click();
    }

    public String getProductText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productText));
        return productText.getText();
    }

    public WebElement getShoppingCartText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(shoppingCart));
    }

    public String getErrorTextOnLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorText));
        return errorText.getText();
    }
}

