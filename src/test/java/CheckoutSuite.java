import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutSuite {

    WebDriver driver;
    WebDriverWait wait;

    @Parameters("browser")
    @BeforeTest
    public void startBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login to the site
        login("standard_user", "secret_sauce");
    }
    @AfterTest
    public void stopBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 0)
    public void addProducts() {
        addProductToCart("sauce-labs-backpack");
        addProductToCart("sauce-labs-bike-light");
    }

    @Test(priority = 1)
    public void checkout() {
        // Navigate to cart and initiate checkout
        clickElement(By.cssSelector(".shopping_cart_link"));
        clickElement(By.cssSelector("#checkout"));

        // Fill in checkout information
        fillCheckoutInformation("Abdulmalik", "Testoro", "700107");

        // Scroll down and continue with the checkout
        scrollToBottom();
        clickElement(By.cssSelector("#continue"));

        // Scroll down and finish the checkout
        scrollToBottom();
        clickElement(By.cssSelector("#finish"));

        // Return to products page
        clickElement(By.cssSelector("#back-to-products"));
    }

    // Helper Methods

    private void login(String username, String password) {
        sendKeys(By.cssSelector("#user-name"), username);
        sendKeys(By.cssSelector("#password"), password);
        clickElement(By.cssSelector("#login-button"));
    }

    private void addProductToCart(String productName) {
        String addToCartButton = String.format("#add-to-cart-%s", productName);
        clickElement(By.cssSelector(addToCartButton));
    }

    private void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        sendKeys(By.cssSelector("#first-name"), firstName);
        sendKeys(By.cssSelector("#last-name"), lastName);
        sendKeys(By.cssSelector("#postal-code"), postalCode);
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    private void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}