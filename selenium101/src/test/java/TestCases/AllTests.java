package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.UnhandledException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.backgroundservice.BackgroundService;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AllTests {

    WebDriver driver;

    @BeforeTest
    public void initBrowser(){
        System.out.println("Initiate Browser");
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser opened");
    }

    @AfterTest
    public void closeBrowser(){
        System.out.println("Close Browser");
        //this.driver.quit();
    }

    //@Test
    public void test1() throws InterruptedException {
        System.out.println("Test Started.");
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[text()='Simple Form Demo']")).click();
        Thread.sleep(3000);
        String actualURL = driver.getCurrentUrl();
        String URLTOCONTAIN = "simple-form-demo";
        Assert.assertTrue(actualURL.contains(URLTOCONTAIN));
        System.out.println("*** Assertion Passed : URL contains text ***");
        String inputMsg = "Welcome to LambdaTest";
        driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(inputMsg);
        driver.findElement(By.id("showInput")).click();
        Thread.sleep(3000);
        String actualMsg = driver.findElement(By.xpath("//div[@id='user-message']/p")).getText();
        Assert.assertTrue(inputMsg.equals(actualMsg));
        System.out.println("*** Assertion Passed : Input Text msg == Actual Text message ***");
        System.out.println("Test case complete");
    }

    //@Test
    public void test2() throws InterruptedException{
        System.out.println("Test Started.");
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[text()='Drag & Drop Sliders']")).click();
        Thread.sleep(3000);
        WebElement slider = driver.findElement(By.xpath("//h4[contains(text(),'Default value 15')]/following::div/input"));
        Actions a = new Actions(driver);
        a.dragAndDropBy(slider, 119, 0).build().perform();
        String res = driver.findElement(By.id("rangeSuccess")).getText();
        Assert.assertEquals(res,"95");
        System.out.println("Assertion Passed: 95");
    }

    
}
