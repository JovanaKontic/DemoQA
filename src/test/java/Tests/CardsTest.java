package Tests;

import Base.DemoQaBase;
import Pages.FirstPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class CardsTest extends DemoQaBase {
// This test class is to check "PING" cards on the main page (https://demoqa.com/)
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
    }

    @Test (priority = 10)
    public void elementsCard () {
        clickingOnElementInListUsingText(firstPage.cardList,"Elements");
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
    }
    /*
    sve treba petlje promeniti sa clickingOnElementInListUsingText i onda promeniti ostalo
     */
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
//        driver.manage().deleteAllCookies();
//        driver.close();
    }

}
