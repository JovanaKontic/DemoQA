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

import java.time.Duration;

public class ButtonsTest extends DemoQaBase {
    String titleOfTheButtonsPage = "Buttons";
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(elementsPageURL); //every test starts from elements page
        elementsPage = new ElementsPage();
        firstPage = new FirstPage(); // only for logo button testing
        /*
        here we load pages we need
         */
    }
    @Test(priority = 10)
    public void shouldGoToButtonsPage ()  {
        clickingOnElementInListUsingText (elementsPage.elementsPageList,  "Buttons");
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
    public void shouldDoubleClickOnDoubleClickMeWithJavaScript() {
        /*
        https://www.browserstack.com/guide/double-click-in-selenium or ...JS
         */
        shouldGoToButtonsPage();
        doubleClickWithJS(elementsPage.doubleClickButton);
        Assert.assertTrue(IsDisplayed(elementsPage.doubleClickMessage));
        Assert.assertTrue(elementsPage.doubleClickMessage.getText().contains("You have done a double click"));
    }
    @Test(priority = 40)
    public void shouldDoubleClickOnDoubleClickMeWithActionsClass() {
        /*
        https://www.browserstack.com/guide/double-click-in-selenium or ...JS
         */
        Actions actions = new Actions(driver);
        shouldGoToButtonsPage();
        actions.doubleClick(elementsPage.doubleClickButton).perform();
        Assert.assertTrue(IsDisplayed(elementsPage.doubleClickMessage));
        Assert.assertTrue(elementsPage.doubleClickMessage.getText().contains("You have done a double click"));
    }
    /*
    MORE HELPFULL LINKS
    https://stackoverflow.com/questions/24749405/double-click-through-javascript-execution-for-selenium
    https://www.geeksforgeeks.org/how-to-perform-right-click-using-java-in-selenium/
     */
    @Test (priority = 50)
    public void shouldRightClickOnRightClickMeWithActionsClass() {
        Actions actions = new Actions(driver);
        shouldGoToButtonsPage();
        actions.contextClick(elementsPage.rightClickButton).perform();
        Assert.assertTrue(IsDisplayed(elementsPage.rightClickMessage));
        Assert.assertTrue(elementsPage.rightClickMessage.getText().contains("You have done a right click"));
    }
    @Test (priority = 00)
    public void shouldClickOnClickMeWithJS() {
        /*
        .click i action class ne rade
         */
        shouldGoToButtonsPage();
//        elementsPage.justClickButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.justClickMessage));
        Assert.assertTrue(elementsPage.justClickMessage.getText().contains("You have done a dynamic click"));
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
