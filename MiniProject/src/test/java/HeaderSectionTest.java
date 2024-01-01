import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

public class HeaderSectionTest {

    public WebDriver driver;

    @DataProvider
    public Object[][] logindata() {
        return new Object[][]{
                {"rahul", "rahul@2021"},
        };
    }

    ;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @Test(priority = 1, dataProvider = "logindata")
    public void WebsiteLogo(String username, String Password) {

        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);
        //password
        WebElement passwordel = driver.findElement(By.id("passwordInput"));
        passwordel.sendKeys(Password);
        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();
        //log wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/a/img")));
        //assert
        Assert.assertTrue(logo.isDisplayed(), "Webssite logo is visible");

    }

    @Test(priority = 2,dataProvider = "logindata")
    public void testNavbarElements(String username, String Password) {
        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);
        //password
        WebElement passwordel = driver.findElement(By.id("passwordInput"));
        passwordel.sendKeys(Password);
        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();
        // Wait for the Navbar to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement navbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/ul")));

        // Check if the Navbar is Displayed
        Assert.assertTrue(navbar.isDisplayed(), "Navbar is not displayed");

        // Find and Test Each Navbar Element
        List<WebElement> navbarElements = navbar.findElements(By.tagName("li"));

        for (WebElement element : navbarElements) {
            // Check if each element is displayed
            Assert.assertTrue(element.isDisplayed(), "Navbar element is not displayed: " + element.getText());
        }

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
