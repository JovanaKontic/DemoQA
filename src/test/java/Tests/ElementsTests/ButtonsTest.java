package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;

public class ButtonsTest extends DemoQaBase {
    String titleOfTheButtonsPage = "Buttons";
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().window().maximize();
        driver.get(elementsPageURL); //every test starts from elements page
        elementsPage = new ElementsPage();
        firstPage = new FirstPage(); // only for logo button testing
        /*
        here we load pages we need
         */
    }
    @Test(priority = 10)
    public void shouldGoToButtonsPage ()  {
        elementsPage.clickOnTheButtonFromTheElementsMenu("Buttons");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheButtonsPage));
        Assert.assertEquals(driver.getCurrentUrl(),buttonsPageURL);
    }
    @Test(priority = 20)
    public void shouldGoToHomePageByClickingLogoButtonFromButtonsPage() {
        shouldGoToButtonsPage();
        firstPage.logoButton.click();                                               //when click on the logo return to the home page
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test(priority = 30)
    public void ShouldDoubleClickOnDoubleClickMeWithJavaScript () {
        /*
        https://www.browserstack.com/guide/double-click-in-selenium or ...JS
         */
        shouldGoToButtonsPage();
        doubleClickWithJS(elementsPage.doubleClickButton);
        Assert.assertTrue(IsDisplayed(elementsPage.doubleClickMessage));
    }
    @Test(priority = 40)
    public void ShouldDoubleClickOnDoubleClickMeWithActionsClass () {
        /*
        https://www.browserstack.com/guide/double-click-in-selenium or ...JS
         */
        Actions actions = new Actions(driver);
        shouldGoToButtonsPage();
        actions.doubleClick(elementsPage.doubleClickButton).perform();
    }

    /*
    checking other buttons
     */




    @AfterMethod
    public void shutDownTest () {
//        driver.manage().deleteAllCookies();
//        driver.close();
    }

}
