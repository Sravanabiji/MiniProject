
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


public class HomePageTest {

    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech/");
    }

    @DataProvider
    public Object[][] logindata() {
        return new Object[][]{
                {"rahul", "rahul@2021"},
        };
    }

    ;


    @Test(priority = 1, dataProvider = "logindata")
    public void ValidCredentials(String username, String password) {

        //username
        WebElement usernameEl = driver.findElement(By.id("usernameInput"));
        usernameEl.sendKeys(username);
        //password
        WebElement passwordEl = driver.findElement(By.id("passwordInput"));
        passwordEl.sendKeys(password);
        //button
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        button.click();
    }

    @Test(priority = 2)
    public void Heading() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //movies
        WebElement moviesHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/a/img")));
        Assert.assertEquals(moviesHeading.getAttribute("alt"), "website logo");
        // Home
        WebElement homeHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/ul/li[1]/a")));
        Assert.assertEquals(homeHeading.getText(), "Home");

        // Popular
        WebElement popularHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/nav/div[1]/ul/li[2]/a")));
        Assert.assertEquals(popularHeading.getText(), "Popular");

        //play button
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/button"));
        Assert.assertTrue(button.isDisplayed(), "false");
    }

    @Test(priority = 3)
    public void movieslist() {
        // Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // List
        List<WebElement> moviesList = driver.findElements(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/div/div"));

        // Check if there are movies
        Assert.assertFalse(moviesList.isEmpty(), "No movies found in the Movies section");

        // Iterate through each movie
        for (int i = 1; i <= moviesList.size(); i++) {
            // Construct the xpath for each movie
            String movieXPath = String.format("/html/body/div/div/div[2]/div[1]/div/div/div/div/div[%d]", i);

            // Find the movie element using xpath
            WebElement movie = driver.findElement(By.xpath(movieXPath));

            // Wait for the movie to be visible
            wait.until(ExpectedConditions.visibilityOf(movie));

            // Check if the movie is displayed
            Assert.assertTrue(movie.isDisplayed(), "Movie is not displayed");
        }


    }

    @Test(priority = 4)
    public void ContactUs() {
        // Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the contact us section by class name
        WebElement contactUsSection = driver.findElement(By.className("footer-container"));

        // Wait for the contact us section to be visible
        wait.until(ExpectedConditions.visibilityOf(contactUsSection));

        // Check if the contact us section is displayed
        Assert.assertTrue(contactUsSection.isDisplayed(), "Contact Us section is not displayed");

    }

    //aftertest
    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
