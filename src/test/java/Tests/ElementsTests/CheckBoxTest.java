package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckBoxTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);                                                  //every test starts from the home page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    public void goToElementsPage() {                                               //getting to the elements page by clicking on the elements card on the home page
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains("Elements"));
    }
    @Test(priority = 30)
    public void shouldGoToCheckBoxPage()   {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Check Box");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains("Check Box"));
    }
    @Test (priority = 40)
    public void checkBoxArrowsFlow ()   {
        shouldGoToCheckBoxPage();
        checkBoxPage.homeArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.desktopCheckBox));
        checkBoxPage.desktopArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.notesCheckBox));
        checkBoxPage.documentsArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.workspaceCheckBox));
        checkBoxPage.workspaceArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.reactCheckBox));
        checkBoxPage.officeArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.publicCheckBox));
        checkBoxPage.downloadsArrow.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.wordFileCheckBox));
    }
    @Test (priority = 50)
    public void checkBoxesFlow () {
        shouldGoToCheckBoxPage();
        checkBoxPage.plusSign.click();
        checkBoxPage.homeCheckBox.click();
        Assert.assertTrue(checkBoxPage.getNotificationText().contains("home"));
        checkBoxPage.homeCheckBox.click();
        Assert.assertFalse(IsDisplayed(checkBoxPage.result));
        checkBoxPage.minusSign.click();
        Assert.assertFalse(IsDisplayed(checkBoxPage.desktopCheckBox));
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
