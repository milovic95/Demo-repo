 
 
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
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;



public class PonedeljakTest {
    private WebDriver driver;
    private WebDriverWait wait;

@Before 
  public void setup () {
    WebDriverManager.chromedriver().setup();
    driver= new ChromeDriver();
    wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    driver.manage().window().maximize();
  }
@Test 
   public void sortTest () {

          try{
     driver.get("https://www.saucedemo.com/");
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
     driver.findElement(By.id("user-name")).sendKeys("standard_user");
     driver.findElement(By.id("password")).sendKeys("secret_sauce");
     driver.findElement(By.id("login-button")).click();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link")));
     WebElement sortDropdown=
     driver.findElement(By.className("product_sort_container"));
     Select select= new Select(sortDropdown);
     select.selectByVisibleText("Price (high to low)");
     List<WebElement> addButtons=
     driver.findElements(By.className("btn_inventory"));
             addButtons.get(0).click();
             addButtons.get(1).click();
    driver.findElement(By.className("shopping_cart_link")).click();
     List<WebElement>cartItems=
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("cart_item")));
            assertEquals(2, cartItems.size());
    driver.findElement(By.id("checkout")).click();
    driver.findElement(By.id("first-name")).sendKeys("Pavle");
    driver.findElement(By.id("last-name")).sendKeys("Milovic");
    driver.findElement(By.id("postal-code")).sendKeys("11300");
    driver.findElement(By.id("continue")).click();
    driver.findElement(By.id("finish")).click();
     WebElement thankYoumsg=
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
            assertEquals("Thank you for your order!", thankYoumsg.getText());
             Thread.sleep(5000);

           } catch (Exception e) {
    e.printStackTrace();
    fail("Test failed: " + e.getMessage());
      }
  }

@After 

   public void teardown () {
    driver.quit();
   }
    
}
