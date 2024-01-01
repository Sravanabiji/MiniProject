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


public class MovieDetailsPageTest {

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
    public void MovieDetailsPageTest(String username,String password){

        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);

        //password
        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);

        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();

        //movie details

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement MovieDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/div/div/div[1]/div/a/div/img")));
        MovieDetails.click();

        // Check Movie Title
        WebElement movieTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/h1")));
        Assert.assertTrue(movieTitle.isDisplayed(), "Movie title is not displayed");

        // Check Movie Description
        WebElement movieDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/p")));
        Assert.assertTrue(movieDescription.isDisplayed(), "Movie description is not displayed");

        // Check Play Button
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/button")));
        Assert.assertTrue(playButton.isDisplayed(), "Play button is not displayed");
    }

    @Test(priority = 2,dataProvider = "logindata")
    public void PopularPage(String username, String password){
        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);

        //password
        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);

        //button
        WebElement button = driver.findElement(By.className("login-button"));
        button.click();

        //popular movie page

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement popularmovie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/ul/li[2]/a")));
        popularmovie.click();

        //movie
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement movie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/li[1]/a/img")));
        movie.click();

        // Check Movie Title
        WebElement movieTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/h1")));
        Assert.assertTrue(movieTitle.isDisplayed(), "Movie title is not displayed");

        // Check Movie Description
        WebElement movieDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/p")));
        Assert.assertTrue(movieDescription.isDisplayed(), "Movie description is not displayed");

        // Check Play Button
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/button")));
        Assert.assertTrue(playButton.isDisplayed(), "Play button is not displayed");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}