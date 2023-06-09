package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.FirstPage;
import Pages.ElementsPage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.ArrayList;

public class ElementsPageTest extends DemoQaBase {
    String titleOfTheElementsPage = "Elements";
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);                                                    //every test starts from the home page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    public void goToElementsPage() {                                               //getting to the elements page by clicking on the elements card on the home page
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheElementsPage));
    }
    @Test (priority = 10)
    public void shouldReturnToHomePageAfterClickingOnLogoButton() {
        goToElementsPage();
        firstPage.logoButton.click();                                               //when click on the logo return to the home page
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 20)
    public void elementsMenuShouldRollUp() {
        goToElementsPage();
        Assert.assertTrue(IsDisplayed(elementsPage.elementsRollDown));             //asserting that we see roll-down menu when we click on the elements button
        Assert.assertTrue(IsDisplayed(elementsPage.arrowUp));                       //arrow is UP on the elements button
        elementsPage.clickOnElementsButton();
        Assert.assertFalse(IsDisplayed(elementsPage.elementsRollDown));             //asserting that we don't see roll-down menu when we click on the elements button
        Assert.assertTrue(IsDisplayed(elementsPage.arrowDown));                     //arrow is DOWN on the elements button
    }
//    ==================================================================================================================


    @Test (priority = 140)
    public void buttonsCheck () {
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Buttons");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 150)
    public void buttonsDoubleClick () {
        buttonsCheck();
        doubleClickWithJS(elementsPage.doubleClickButton);
        Assert.assertTrue(IsDisplayed(elementsPage.doubleClickMessage));
    }
    @Test (priority = 160)
    public void buttonsRightClick () {
        Actions action = new Actions(driver);
        buttonsCheck();
        action.contextClick(elementsPage.rightClickButton).perform();
        Assert.assertTrue(IsDisplayed(elementsPage.rightClickMessage));
    }
    @Test (priority = 170)
    public void buttonsJustClick () {
        buttonsCheck();
        elementsPage.clickOnJustClick();
        Assert.assertTrue(IsDisplayed(elementsPage.justClickMessage));
    }

//    ==================================================================================================================
    @Test (priority = 180)
    public void linksCheck () {
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Links");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 190)
    public void linksHome () {
       linksCheck();
       String oldTab = driver.getWindowHandle();
       elementsPage.simpleLink.click();
       ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
       newTab.remove(oldTab);
       driver.switchTo().window(newTab.get(0));
       Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
    }
    @Test (priority = 200)
    public void linksHomeDynamic () {
        linksCheck();
        String oldTab = driver.getWindowHandle();
        elementsPage.dynamicLink.click();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
    }
    @Test (priority = 210)
    public void linksCreated () {
        linksCheck();
        elementsPage.createdLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("201"));
    }
    @Test (priority = 220)
    public void linksNoContent () {
        linksCheck();
        elementsPage.noContentLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("204"));
    }
    @Test (priority = 230)
    public void linksMoved ()   {
        linksCheck();
        elementsPage.movedLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("301"));
    }
    @Test (priority = 240)
    public void linksBadRequest ()   {
        linksCheck();
        elementsPage.badRequestLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("400"));
    }
    @Test (priority = 250)
    public void linksUnauthorized ()   {
        linksCheck();
        elementsPage.unauthorizedLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("401"));
    }
    @Test (priority = 260)
    public void linksForbidden ()   {
        linksCheck();
        elementsPage.forbiddenLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("403"));
    }
    @Test (priority = 270)
    public void linksNotFound ()   {
        linksCheck();
        elementsPage.notFoundLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("404"));
    }

//    ==================================================================================================================
    @Test (priority = 280)
    public void brokenLinksCheck ()   {
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Broken Links - Images");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 290)
    public void brokenlinksValidLink ()   {
        brokenLinksCheck();
        elementsPage.validLink.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 300)
    public void brokenlinksBrokenLink()   {
        brokenLinksCheck();
        elementsPage.brokenLink.click();
        Assert.assertEquals(driver.getCurrentUrl(),brokenLinkURL);
    }

//    ==================================================================================================================
    @Test (priority = 310)
    public void uploadAndDownloadCheck ()   {
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Upload and Download");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 320)
    public void downloadValidation () {
        uploadAndDownloadCheck();
        elementsPage.downloadButton.click();
//      KAKO ASERTOVATI DA JE FAJL SKINUT ?? LINK RESPONSE 200 ???
    }
//    @Test (priority = 330)
//    public void uploadValidation ()   {
//        uploadAndDownloadCheck();
//        waitForClickability(elementsPage.uploadFileButton);
//        elementsPage.uploadFileButton.click();
//        //      KAKO ASERTOVATI DA JE FAJL IZABRAN ??
//    }

//    ==================================================================================================================
    @Test (priority = 340)
    public void dynamicPropertiesCheck ()   {
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Dynamic Properties");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
//    @Test (priority = 350)
//    public void randomIDValidation ()   {    // KAKO DA ASERTUJEM AKO MU SE MENJA ID
//        dynamicPropertiesCheck ();
//        waitForVisibility(elementsPage.randomIDText);
//        Assert.assertTrue(elementsPage.randomIDText.getText().contains("This text has random Id"));
//    }
    @Test (priority = 350)
    public void enableAfterButtonValidation ()   {
        dynamicPropertiesCheck ();
        waitForClickability(elementsPage.enableAfterButton);
        Assert.assertTrue(IsEnabled(elementsPage.enableAfterButton));
    }
    @Test (priority = 360)
    public void colorChangeValidation ()   {
        dynamicPropertiesCheck ();
        Assert.assertFalse(IsDisplayed(elementsPage.colorChangeButton));
        waitForVisibility(elementsPage.colorChangeButton);
        Assert.assertTrue(IsDisplayed(elementsPage.colorChangeButton));
    }
    @Test (priority = 370)
    public void visibleAfterValidation ()   {
        dynamicPropertiesCheck ();
        Assert.assertFalse(IsDisplayed(elementsPage.visibleAfterButton));
        waitForVisibility(elementsPage.visibleAfterButton);
        Assert.assertTrue(IsDisplayed(elementsPage.visibleAfterButton));
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}

//                                      SCROOL DOWN
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");


//                                      DOUBLE CLICK
//    public void doubleClickWithJS(WebElement element) {
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', { bubbles: true }));", element);
//    }







