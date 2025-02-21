import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.login.LoginPagePo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckLoginPage {
    private String url = "https://www.saucedemo.com/";
    private WebDriver driver;
    private LoginPagePo objLoginPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver();
        objLoginPage = new LoginPagePo(driver);
    }

    @Test
    @Parameters({"incorrectUsername", "incorrectPassword"})
    public void checkIncorrectLogin(String incorrectUsername, String incorrectPassword) {
        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(incorrectUsername, incorrectPassword);

        String getErrorTextOnLogin = objLoginPage.getErrorTextOnLogin(driver);
        assertEquals(getErrorTextOnLogin, "Epic sadface: Username and password do not match any user in this service");
    }


    @Test
    @Parameters({"username", "password"})
    public void checkCorrectLogin(String username, String password) {
        driver.get(url);
        objLoginPage.enterCredentialsAndSubmit(username, password);

        String actualTextProductElement = objLoginPage.getProductText(driver);
        WebElement actualTextShoppingCart = objLoginPage.getShoppingCartText(driver);

        // Assert that the actual text matches the expected text
        assertTrue(actualTextShoppingCart.isDisplayed());
        assertEquals(actualTextProductElement, "Products");
    }

    @AfterClass
    public void closeBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }
}

