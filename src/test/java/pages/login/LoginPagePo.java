package pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPagePo {
    WebDriver driver;
    public By login = By.id("user-name");
    public By password = By.id("password");
    public By loginButton = By.cssSelector("#login-button");
    public By productText = By.cssSelector("[data-test=\"title\"]");
    public By shoppingCart = By.cssSelector("[data-test=\"shopping-cart-link\"]");
    public By errorText = By.cssSelector("[data-test=\"error\"]");

    public LoginPagePo(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCredentialsAndSubmit(String login, String password) {
        driver.findElement(this.login).sendKeys(login);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getProductText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productText));
        return productElement.getText();
    }

    public WebElement getShoppingCartText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCart));
    }

    public String getErrorTextOnLogin(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorTextMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorText));
        return errorTextMessage.getText();
    }
}

