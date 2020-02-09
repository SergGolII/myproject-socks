import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainPage {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void openMainPage(){
        String baseUrl = "http://167.172.110.35/";
        String pageTitle = "WeaveSocks";

        driver.get(baseUrl);
        assertEquals(driver.getCurrentUrl(), baseUrl);
        assertEquals(driver.getTitle(), pageTitle);

    }

    WebElement loginButton = driver.findElement(By.id("login")).click();
    WebElement loginUsername = driver.findElement(By.id("login-message")).sendKeys("Pablo");
    WebElement loginPassword = driver.findElement(By.id("password-modal")).sendKeys("qwerty");
    WebElement loginAction = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/form/p/button")).click();

}
