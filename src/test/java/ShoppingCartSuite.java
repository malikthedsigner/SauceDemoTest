import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingCartSuite {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login to the application
        login("standard_user", "secret_sauce");
    }

    @AfterTest
    public void stopBrowser() {
        driver.quit();
    }

    @Test(priority = 0)
    public void addProductToCart() {
        clickElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
    }

    @Test(priority = 1)
    public void removeProductFromCart() {
        // Navigate to the shopping cart
        clickElement(By.cssSelector(".shopping_cart_link"));

        // Remove the product from the cart
        clickElement(By.cssSelector("#remove-sauce-labs-backpack"));

        // Continue shopping
        clickElement(By.cssSelector("#continue-shopping"));
    }

    // Helper Methods

    private void login(String username, String password) {
        sendKeys(By.cssSelector("#user-name"), username);
        sendKeys(By.cssSelector("#password"), password);
        clickElement(By.cssSelector("#login-button"));
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
}
