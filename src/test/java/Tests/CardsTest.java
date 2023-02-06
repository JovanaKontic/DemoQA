package Tests;

import Base.DemoQaBase;
import Pages.FirstPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class CardsTest extends DemoQaBase {

    @BeforeMethod
    public void setUpPage () {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        ChromeDriver driver1 = new ChromeDriver(options);
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
    }

    @Test (priority = 10)
    public void elementsCard () {
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
    }
    @Test (priority = 20)

    public void formsCard () {
        firstPage.clickOnFormsCard();
        Assert.assertEquals(driver.getCurrentUrl(),formsPageURL);
    }
    @Test (priority = 30)
    public void alertsCard () {
        firstPage.clickOnAlertsCard();
        Assert.assertEquals(driver.getCurrentUrl(),alertsPageURL);
    }
    @Test (priority = 40)
    public void widgetsCard () {
        firstPage.clickOnWidgetsCard();
        Assert.assertEquals(driver.getCurrentUrl(),widgetsPageURL);
    }
    @Test (priority = 50)
    public void interationsCard () {
        firstPage.clickOnInteractionsCard();
        Assert.assertEquals(driver.getCurrentUrl(),interactionPageURL);
    }
    @Test (priority = 60)
    public void bookStoreCard () {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        firstPage.clickOnBookstoreCard();
        Assert.assertEquals(driver.getCurrentUrl(),bookStorePageURL);
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
