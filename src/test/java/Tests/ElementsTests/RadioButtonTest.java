package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RadioButtonTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().window().maximize();
        driver.get(homePageURL);                                                  //every test starts from the home page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    public void goToElementsPage() {                                               //getting to the elements page by clicking on the elements card on the home page
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains("Elements"));
    }
    @Test(priority = 10)
    public void shouldGoToRadioButtonPage() {   //going to radio page in elements card
        goToElementsPage();
        elementsPage.clickOnTheButtonFromTheElementsMenu("Radio Button");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains("Radio Button"));
    }
    @Test (priority = 20)
    public void shouldHaveQuestionDisplayed() {     //is text displayed on the page,
        shouldGoToRadioButtonPage();
        Assert.assertTrue(elementsPage.radioButtonQuestionText.getText().equalsIgnoreCase("Do you like the site?"));
    }
    @Test (priority = 30)
    public void shouldRadioButtonYesWork () {   //is YES clickable
        shouldGoToRadioButtonPage();
        elementsPage.yesRadioButton.click();
        Assert.assertTrue(elementsPage.getTextFromWebElement(elementsPage.radioButtonMessage).contains("You have selected Yes"));
    }
    @Test (priority = 40)
    public void shouldRadioButtonImpressiveWork () {        //is impressive clickable
        shouldGoToRadioButtonPage();
        elementsPage.impressiveRadioButton.click();
        Assert.assertTrue(elementsPage.getTextFromWebElement(elementsPage.radioButtonMessage).contains("You have selected Impressive"));
    }
    @Test (priority = 50)
    public void shouldRadioButtonNoWork() { // button NO is not working, becouse it is meant to not work
        // BITNO CSS VALUE SE VADI IZ INSPECT PA DOLE STYLES PA KURSOR PA VREDNOST
        shouldGoToRadioButtonPage();
        String noClickPlace = elementsPage.noRadioButton.getCssValue("cursor");
        Assert.assertEquals(noClickPlace, "not-allowed");
    }
    @Test (priority = 60)
    public void shouldRadioButtonsWorkWhenChangingSelectionFlow() {    //doing a flow to see if the text changes and also if the buttons can be clicked after one another
        shouldGoToRadioButtonPage();
        elementsPage.yesRadioButton.click();
        Assert.assertTrue(elementsPage.getTextFromWebElement(elementsPage.radioButtonMessage).contains("You have selected Yes"));
        elementsPage.impressiveRadioButton.click();
        Assert.assertTrue(elementsPage.getTextFromWebElement(elementsPage.radioButtonMessage).contains("You have selected Impressive"));
        elementsPage.yesRadioButton.click();
        Assert.assertTrue(elementsPage.getTextFromWebElement(elementsPage.radioButtonMessage).contains("You have selected Yes"));
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
