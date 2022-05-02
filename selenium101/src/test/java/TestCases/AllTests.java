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
import org.openqa.selenium.support.ui.Select;
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

    @Test
    public void test3() throws InterruptedException{
        System.out.println("Test Started.");
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.findElement(By.xpath("//a[text()='Input Form Submit']")).click();
        Thread.sleep(3000);
        /**
         * Fill the fields
         */

        driver.findElement(By.id("name")).sendKeys("John Doe");
        driver.findElement(By.id("inputEmail4")).sendKeys("JohnDoe@test.com");
        driver.findElement(By.id("inputPassword4")).sendKeys("Test123");
        driver.findElement(By.id("company")).sendKeys("TestCompany");
        driver.findElement(By.id("websitename")).sendKeys("TestCompany.com");
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select s = new Select(countryDropdown);
        s.selectByValue("CA");
        driver.findElement(By.id("inputCity")).sendKeys("Test City");
        driver.findElement(By.id("inputAddress2")).sendKeys("123 Test Street");
        driver.findElement(By.id("inputAddress1")).sendKeys("Near TestLandmark");
        driver.findElement(By.id("inputState")).sendKeys("Test State");
        driver.findElement(By.id("inputZip")).sendKeys("12345");


        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
        String actSuccessMsg = driver.findElement(By.xpath("(//form[@id='seleniumform']/following::p)[1]")).getText();
        String EXP_MSG = "Thanks for contacting us, we will get back to you shortly.";
        Assert.assertEquals(actSuccessMsg,EXP_MSG);
        System.out.println("Assertion Passed - ***");
    }

}
