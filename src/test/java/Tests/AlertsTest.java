package Tests;

import Base.DemoQaBase;

import Pages.AlertsPage;
import Pages.FirstPage;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;


public class AlertsTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
        alertsPage = new AlertsPage();
    }
    public void goToAlertsPage () {
        firstPage.clickOnAlertsCard();
        Assert.assertEquals(driver.getCurrentUrl(),alertsPageURL);
    }
    @Test (priority = 9)
    public void logoButtonChecking() {
        goToAlertsPage ();
        firstPage.logoButton.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test(priority = 10)
    public void formsCardRollUp () {
        goToAlertsPage ();
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
//        Thread.sleep(3000);
        alertsPage.clickOnAlertsButton();
        Assert.assertFalse(IsDisplayed(alertsPage.alertsRollDown));
    }

    //==================================================================================================================
    @Test (priority = 20)
    public void browserWindowsCheck () {
        goToAlertsPage ();
        alertsPage.clickOnAlertsMenuButton("Browser Windows");
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
    }
    @Test (priority = 30)
    public void newTabCheck () {
        browserWindowsCheck ();
        String oldTab = driver.getWindowHandle();
        alertsPage.newTabButton.click();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        Assert.assertEquals(driver.getCurrentUrl(),newTabAlertsURL);
    }
    @Test (priority = 40)
    public void newWindowCheck () {
        browserWindowsCheck ();
        String oldTab = driver.getWindowHandle();
        alertsPage.newWindowButton.click();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        Assert.assertEquals(driver.getCurrentUrl(),newTabAlertsURL);
    }
//    @Test (priority = 50)
//    public void newWindowMessageCheck () {
//        browserWindowsCheck ();
//        String oldTab = driver.getWindowHandle();
//        alertsPage.newWindowMessageButton.click();
//        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
//        newTab.remove(oldTab);
//        driver.switchTo().window(newTab.get(0));
//        Assert.assertTrue(IsDisplayed(alertsPage.newWindowMessageText));
//        driver.quit();
//        driver.switchTo().window(oldTab);
//    }

    //==================================================================================================================
    @Test (priority = 60)
    public void alertsCheck () {
        goToAlertsPage ();
        alertsPage.clickOnAlertsMenuButton("Alerts");
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
    }
    @Test (priority = 70)
    public void alertButtonCheck () {
        alertsCheck();
        alertsPage.alertButton.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("You clicked a button"));
        driver.switchTo().alert().accept();
    }
    @Test (priority = 80)
    public void alertWaitButtonCheck () throws InterruptedException {
        alertsCheck();
        alertsPage.alertWaitButton.click();
        Thread.sleep(6000);
        Assert.assertTrue(driver.switchTo().alert().getText().contains("This alert appeared after 5 seconds"));
        driver.switchTo().alert().accept();
    }
    @Test (priority = 90)
    public void confirmBoxCheckYes () {
        alertsCheck();
        alertsPage.confirmBoxButton.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Do you confirm action?"));
        driver.switchTo().alert().accept();
        Assert.assertTrue(alertsPage.confirmMessage.getText().contains("Ok"));
    }
    @Test (priority = 100)
    public void confirmBoxCheckNo () {
        alertsCheck();
        alertsPage.confirmBoxButton.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Do you confirm action?"));
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(alertsPage.confirmMessage.getText().contains("Cancel"));
    }
    @Test (priority = 110)
    public void promptBoxCheckOk () {
        String firstName = excelReader.getStringData("PracticeForms", 0, 1);
        alertsCheck();
        alertsPage.promptBox.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Please enter your name"));
        driver.switchTo().alert().sendKeys(firstName);
        driver.switchTo().alert().accept();
        Assert.assertTrue(alertsPage.promptMessage.getText().contains(firstName));
    }
    @Test (priority = 120)
    public void promptBoxCheckNo() throws InterruptedException {
        String firstName = excelReader.getStringData("PracticeForms", 0, 1);
        alertsCheck();
        alertsPage.promptBox.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Please enter your name"));
        driver.switchTo().alert().sendKeys(firstName);
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(IsEnabled(alertsPage.promptBox));
    }
    @Test (priority = 130)
    public void promptBoxCheckEmptyOK () {
        alertsCheck();
        alertsPage.promptBox.click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Please enter your name"));
        driver.switchTo().alert().accept();
        Assert.assertTrue(IsEnabled(alertsPage.promptBox));
    }

    //==================================================================================================================
    @Test (priority = 140)
    public void framesCheck () {
        goToAlertsPage ();
        alertsPage.clickOnAlertsMenuButton("Frames");
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
    }
    @Test (priority = 150)
    public void iframe1Exists () {
        framesCheck ();
        Assert.assertTrue(IsDisplayed(alertsPage.iFrame1));
        driver.switchTo().frame(alertsPage.iFrame1);
        Assert.assertTrue(alertsPage.iFrameText.getText().contains("This is a sample page"));
    }
    @Test (priority = 160)
    public void iframe2Exists () {
        framesCheck ();
        Assert.assertTrue(IsDisplayed(alertsPage.iFrame2));
        driver.switchTo().frame(alertsPage.iFrame2);
        Assert.assertTrue(alertsPage.iFrameText.getText().contains("This is a sample page"));
    }

    //==================================================================================================================
    @Test (priority = 170)
    public void nestedFramesCheck () {
        goToAlertsPage ();
        alertsPage.clickOnAlertsMenuButton("Nested Frames");
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
    }
    @Test (priority = 180)
    public void switchingFrameFlow () {
        nestedFramesCheck();
        Assert.assertTrue(IsDisplayed(alertsPage.iFrame1));
        driver.switchTo().frame(alertsPage.iFrame1);
        Assert.assertTrue(alertsPage.iFrameParentText.getText().contains("Parent frame"));
        driver.switchTo().frame(0);
        Assert.assertTrue(alertsPage.iFrameParentText.getText().contains("Child Iframe"));
    }

    //==================================================================================================================

    @Test (priority = 190)
    public void modalsCheck () {
        goToAlertsPage ();
        alertsPage.clickOnAlertsMenuButton("Modal Dialogs");
        Assert.assertTrue(IsDisplayed(alertsPage.titlePage));
    }
    @Test (priority = 200)
    public void smallModalCheck () throws InterruptedException {
        modalsCheck ();
        alertsPage.smallModalButton.click();
        Assert.assertTrue(IsDisplayed(alertsPage.modalWindow));
        Assert.assertTrue(alertsPage.modalWindow.getText().contains("Small Modal"));
        alertsPage.closeSmallModalButton.click();
        // KAKO DA KLIKNEM VAN PROZORCICA
//        Thread.sleep(3000);
        Assert.assertFalse(IsDisplayed(alertsPage.modalWindow));
    }
    @Test (priority = 210)
    public void smallModalXCheck () {
        modalsCheck ();
        alertsPage.smallModalButton.click();
        Assert.assertTrue(IsDisplayed(alertsPage.modalWindow));
        Assert.assertTrue(alertsPage.modalWindow.getText().contains("Small Modal"));
        alertsPage.closeXModalButton.click();
        Assert.assertFalse(IsDisplayed(alertsPage.modalWindow));
    }
    @Test (priority = 220)
    public void largeModalCheck () {
        modalsCheck ();
        alertsPage.largeModalButton.click();
        Assert.assertTrue(IsDisplayed(alertsPage.modalWindow));
        Assert.assertTrue(alertsPage.modalWindow.getText().contains("Large Modal"));
        alertsPage.closeLargeModalButton.click();
        Assert.assertFalse(IsDisplayed(alertsPage.modalWindow));
    }
    @Test (priority = 230)
    public void largeModalXCheck () {
        modalsCheck ();
        alertsPage.largeModalButton.click();
        Assert.assertTrue(IsDisplayed(alertsPage.modalWindow));
        Assert.assertTrue(alertsPage.modalWindow.getText().contains("Large Modal"));
        alertsPage.closeXModalButton.click();
        Assert.assertFalse(IsDisplayed(alertsPage.modalWindow));
    }

    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
