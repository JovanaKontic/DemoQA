package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LinksTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(elementsPageURL);
        elementsPage = new ElementsPage();
    }
    @Test(priority = 10)
    public void shouldGoToLinksPage ()  {
        clickingOnElementInListUsingText(elementsPage.elementsPageList,"Links");
        //nastavi sa ovim testom i ona testna elementspagetest promeni  svih 9 koji sadrze linkscheck i onda ga izbrisi

    }
    @AfterMethod
    public void shutDownTest () {
//        driver.manage().deleteAllCookies();
//        driver.close();
    }
}
