import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class userManagementSuite {

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
    @Test
    public void logout() {
        // Open the menu and log out
        openMenu();
        clickElement(By.cssSelector("#logout_sidebar_link"));
        System.out.println("User has been logged out successfully.");
    }

    // Helper Methods

    private void login(String username, String password) {
        sendKeys(By.cssSelector("#user-name"), username);
        sendKeys(By.cssSelector("#password"), password);
        clickElement(By.cssSelector("#login-button"));
        System.out.println("Logged in as: " + username);
    }

    private void openMenu() {
        clickElement(By.cssSelector("#react-burger-menu-btn"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logout_sidebar_link")));
        System.out.println("Menu is opened.");
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
}
