import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Automation {
    WebDriver  driver;
    @BeforeAll
    public void setup()
    {
           driver=new ChromeDriver();
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

   @Test
   public void formSubmission() throws InterruptedException {
       driver.get("https://www.digitalunite.com/practice-webform-learners");
       String actualTitle=driver.getTitle();
       Assertions.assertEquals("Practice webform for learners | Digital Unite",actualTitle);
       driver.findElement(By.id("onetrust-accept-btn-handler")).click();
       List<WebElement> form=driver.findElements(By.className("form-control"));
      form.get(0).sendKeys("Test User1");
       form.get(1).sendKeys("01303215612");
       form.get(2).sendKeys("12/12/2020");
       form.get(3).sendKeys("TestUser1@gmail.com");
       form.get(4).sendKeys("learning Automation");
       Thread.sleep(1000);
       JavascriptExecutor js=(JavascriptExecutor) driver;
       js.executeScript("window.scrollTo(0,600)");
       driver.findElement(By.id("edit-uploadocument-upload")).sendKeys("C:\\Users\\DELL\\Downloads\\Resume_Tama Debnath.pdf");
       Thread.sleep(3000);
       driver.findElement(By.id("edit-age")).click();
       driver.findElement(By.id("edit-submit")).click();
       driver.getCurrentUrl();
       String actual=driver.findElement(By.tagName("h1")).getText();
       Assertions.assertEquals("Thank you for your submission!",actual);

   }
   @Test
   public void scrapTableData() throws IOException {
        driver.get("https://dsebd.org/latest_share_price_scroll_by_value.php");
       WebElement table=driver.findElements(By.className("table")).get(1);
       List<WebElement>rows=table.findElements(By.tagName("tbody"));
       FileWriter writer=new FileWriter("./src/test/resources/tableData.txt",true);
       for(WebElement row:rows)
       {
           List<WebElement>cells=row.findElements(By.tagName("tr"));
           for(WebElement cell:cells)
           {
               writer.write(cell.getText());
           }
           writer.write("\n");
       }
       writer.flush();
       writer.close();
   }


    //@AfterAll
    public void quitDriver()
    {
        driver.quit();
    }
}
