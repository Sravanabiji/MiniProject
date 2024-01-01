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

public class SearchPageTest {

    public WebDriver driver;

    @DataProvider
    public Object[][] logindata(){
        return new Object[][]{
                {"rahul","rahul@2021"},
        };
    };

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @Test(priority = 1,dataProvider = "logindata")
    public void loginpage(String username,String password){

        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);

        //password
        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);

        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();


        //searchbutton

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement searchEl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[2]/button[1]")));
        searchEl.click();

        //searchitem
        WebElement searchmovie = driver.findElement(By.xpath("/html/body/div/div/nav/div[2]/div/input"));
        searchmovie.sendKeys("Avatar");


        //searchbutton
        WebElement searchebutton = driver.findElement(By.xpath("/html/body/div/div/nav/div[2]/div/button"));
        searchebutton.click();

        // Wait for search results
        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("movie-icon-item")));

        // Print the count of search results
        int numberOfMovies = searchResults.size();
        System.out.println("Number of search results: " + numberOfMovies);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

