import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }



    @Test(priority = 1)
    public void testLogoImageIsDisplayed() {
        // Locate the logo image element
        WebElement logoImage = driver.findElement(By.xpath("/html/body/div/div/div[1]/img"));

        // Verify if the logo image is displayed
        Assert.assertTrue(logoImage.isDisplayed(), "Logo image is not displayed");
    }

    @Test(priority = 2)
    public void testLoginPageHeading() {
        // Locate the heading element
        WebElement headingElement = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/h1"));

        // Verify if the heading text is "Login"
        Assert.assertEquals(headingElement.getText().trim(), "Login", "Heading text is not 'Login'");
    }
    @Test(priority = 3)
    public void UserName(){
        //username
        WebElement username = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/label"));
        //string
        String usernamEl = username.getText();
        //verify
        Assert.assertEquals(usernamEl, "USERNAME", "Username label text is not as expected");
    }
    @Test(priority = 4)
    public void Password(){
        //password
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/label"));
        //string
        String passwordEl = password.getText();
        //assert
        Assert.assertEquals(passwordEl,"PASSWORD","PASSWORD is not visible");
    }
    @Test(priority = 5)
    public  void button(){
        //button
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));

        // Check if the login button is enabled
        boolean isButtonEnabled = button.isEnabled();

        //assert
        Assert.assertTrue(isButtonEnabled, "Login button is not enabled");
    }

    // Login Page Functionalities

    @Test(priority = 6)
    public void EmptyInputFields(){

        //button
        WebElement LoginButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        LoginButton.click();
        //wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));

        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ErrorMsg = errormsg.getText();
        //assert
        Assert.assertEquals(ErrorMsg,"*Username or password is invalid");

    }

    @Test(priority = 7)
    public void EmptyUserName(){
        //username
        WebElement username = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/input"));

        //password
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/input"));
        password.sendKeys("rahul@2021");
        //button
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        button.click();
        //wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));

        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ErrorMsg = errormsg.getText();
        //assert
        Assert.assertEquals(ErrorMsg,"*Username or password is invalid");
    }



    @AfterMethod
    public  void teardown(){
        driver.quit();
    }
}
