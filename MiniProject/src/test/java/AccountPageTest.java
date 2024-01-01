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

public class AccountPageTest {

    public WebDriver driver;

    @DataProvider
    public Object[][] logindata(){
        return new Object[][]{
                {"rahul", "rahul@2021"}
        };
    };

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @Test(priority = 1,dataProvider = "logindata")
    public  void Account(String username, String password){
        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);

        //password
        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);

        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();

        //account button
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement AccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[2]/button[2]/img")));
        AccountButton.click();

        //username
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement Username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/div[1]/div/p[1]")));

        String displayedusername =Username.getText();
        Assert.assertTrue(displayedusername.contains(username), "Displayed username is not 'rahul'");

        //logout button

        WebElement logoutbutton = driver.findElement(By.xpath("/html/body/div/div/div[1]/div[3]/button"));
        logoutbutton.click();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}