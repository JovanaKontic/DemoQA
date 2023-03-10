package Tests;

import Base.DemoQaBase;
import Pages.FirstPage;
import Pages.WidgetsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WidgetsTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
        widgetsPage = new WidgetsPage();
    }
    public void goToWidgetsPage () {
        firstPage.clickOnWidgetsCard();
        Assert.assertEquals(driver.getCurrentUrl(),widgetsPageURL);
    }
    @Test (priority = 9)
    public void logoButtonChecking() {
        goToWidgetsPage ();
        firstPage.logoButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test(priority = 10)
    public void formsCardRollUp () {
        goToWidgetsPage ();
        Assert.assertTrue(IsDisplayed(widgetsPage.titlePage));
        widgetsPage.clickOnAWidgetsButton();
        Assert.assertFalse(IsDisplayed(widgetsPage.widgetsRollDown));
    }

    //==================================================================================================================
    @Test (priority = 20)
    public void accordianCheck () {
        goToWidgetsPage ();
        widgetsPage.clickOnWidgetsMenuButton("Accordian");
        Assert.assertTrue(IsDisplayed(widgetsPage.titlePage));
    }
    @Test (priority = 30)
    public void accordianTabsRollUpAndDownFlow () {
        accordianCheck ();
        widgetsPage.clickOnAWidgetsButton();
        Assert.assertFalse(IsDisplayed(widgetsPage.widgetsRollDown));
        Assert.assertTrue(widgetsPage.firstParagraphText.getText().contains("Lorem Ipsum"));
        widgetsPage.firstTab.click();
        Assert.assertFalse(IsDisplayed(widgetsPage.tabVisibility));
        waitForClickability(widgetsPage.secondTab);
        widgetsPage.secondTab.click();
        Assert.assertTrue(widgetsPage.secondParagraphText.getText().contains("Contrary to popular belief"));
        widgetsPage.secondTab.click();
        Assert.assertFalse(IsDisplayed(widgetsPage.tabVisibility));
        waitForVisibility( widgetsPage.thirdTab);
        widgetsPage.thirdTab.click();
        Assert.assertTrue(widgetsPage.thirdParagraphText.getText().contains("It is a long established fact"));
        widgetsPage.thirdTab.click();
        Assert.assertFalse(IsDisplayed(widgetsPage.tabVisibility));
    }

    //==================================================================================================================
    @Test (priority = 40)
    public void autoCompleteCheck () {
        goToWidgetsPage ();
        widgetsPage.clickOnWidgetsMenuButton("Auto Complete");
        Assert.assertTrue(IsDisplayed(widgetsPage.titlePage));
    }

//    @Test (priority = 50)
//    public void multiColorTextBox () throws InterruptedException {
//        autoCompleteCheck ();
//        textBoxFieldsInputs(widgetsPage.multiColorTextBox, "ma");
//    }


//    @AfterMethod
//    public void shutDownTest () {
//        driver.manage().deleteAllCookies();
//        driver.close();
//    }
}
