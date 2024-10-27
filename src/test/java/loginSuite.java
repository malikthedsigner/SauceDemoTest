import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class loginSuite {

    WebDriver driver;
    String password = "secret_sauce";
    public String[] userNames = {"standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"};

    @BeforeTest
    public void startBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void userLogin() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        for (String username : userNames) {
            driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("#password")).sendKeys(password);
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("#login-button")).click();
            Thread.sleep(3000);
            driver.navigate().back();
        }
    }
}
