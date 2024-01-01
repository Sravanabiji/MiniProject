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

public class PopularPageTest {

    public WebDriver driver;

    @DataProvider
    public Object[][] logindata(){
        return new Object[][] {
                {"rahul", "rahul@2021"},
        };
    };

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @Test(priority = 1,dataProvider = "logindata")
    public void popularpage(String username,String password){

        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);

        //password

        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);

        //button

        WebElement buttonEl = driver.findElement(By.className("login-button"));
        buttonEl.click();

        //wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement popular = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/ul/li[2]/a")));
        popular.click();


        //popular movies list

        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
        List<WebElement> movies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("search-movies-container")));

        //assert
        Assert.assertTrue(movies.size()>0,"No movies are displayed on the popular page.");
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

