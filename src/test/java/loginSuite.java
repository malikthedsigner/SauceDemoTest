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

public class loginSuite {

    WebDriver driver;
    WebDriverWait wait;
    String password = "secret_sauce";
    public String[] userNames = {"standard_user", "problem_user", "performance_glitch_user"};

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
    public void userLogin() {
        driver.get("https://www.saucedemo.com/");
        for (String username : userNames) {
            login(username, password);
            System.out.println("Attempted login with username: " + username);
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-name")));
        }
    }

    // Helper Method
    private void login(String username, String password) {
        // Clear previous values if present
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-name"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password"))).clear();

        // Enter new login credentials
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();
    }
}
