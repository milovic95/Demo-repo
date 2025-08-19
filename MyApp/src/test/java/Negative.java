import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Negative {
    private WebDriver driver;
    private WebDriverWait wait;

@Before 
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
@Test  public void invalidPassword () {

       try {
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("incorrect");
    driver.findElement(By.id("login-button")).click();
    WebElement errorMsg=
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
             assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg.getText());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Test failed: " + e.getMessage());
                  }

    

    }

@After 
    public void teardown (){
        driver.quit();

    }
        


}


        

