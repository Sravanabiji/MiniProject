import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginPageFunctionalities {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chrome119\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");
    }
    @DataProvider
    public Object[][] logindata(){
        return new Object[][]{
                {"rahul", "rahul@2021"},
        };
    };

    @Test(priority = 1)
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

    @Test(priority = 2)
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

    //test the loginfunctionality  for empty password

    @Test(priority = 3)
    public void EmptyPassword(){
        //username
        WebElement username = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/input"));
        username.sendKeys("rahul");
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
    @Test(priority = 4)
    public  void InvalidCredentials(){
        //username
        WebElement username = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/input"));
        username.sendKeys("Abiji");
        //password
        WebElement password = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/input"));
        password.sendKeys("rahul2021");
        //button
        WebElement button = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        button.click();
        //wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));

        WebElement errormsg = driver.findElement(By.className("error-message"));
        String ErrorMsg = errormsg.getText();
        //assert
        Assert.assertEquals(ErrorMsg,"*invalid username");
    }

    @Test(priority = 5,dataProvider = "logindata")
    public void ValidCredentials(String username, String password){

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
    @AfterMethod
    public  void teardown(){
        driver.quit();
    }
}
