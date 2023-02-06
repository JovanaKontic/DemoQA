package Tests;

import Base.DemoQaBase;
import Pages.FirstPage;
import Pages.FormsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormsTest extends DemoQaBase {

    @BeforeMethod
    public void setUpPage () {
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
        formsPage = new FormsPage();
    }

    public void goToFormsPage () throws InterruptedException {
        Thread.sleep(3000);
        firstPage.clickOnFormsCard();
        Assert.assertEquals(driver.getCurrentUrl(),formsPageURL);
    }
    @Test(priority = 10)
    public void formsCardRollUp () throws InterruptedException {
        goToFormsPage ();
        Assert.assertTrue(IsDisplayed(formsPage.titlePage));
        Thread.sleep(3000);
        formsPage.clickOnFormsButton();
        Assert.assertFalse(IsDisplayed(formsPage.formsRollDown));
    }
    //==================================================================================================================
    @Test (priority = 20)
    public void practiceFormCheck () throws InterruptedException {
        goToFormsPage ();
        formsPage.clickOnFormsMenuButton();
        Assert.assertTrue(IsDisplayed(formsPage.titlePage));
    }
    @Test (priority = 30)
    public void practiceFormEmptyFieldsCantSubmit () throws InterruptedException {
        practiceFormCheck ();
        Assert.assertFalse(IsDisplayed(formsPage.visibleAfterSubmitForms));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", formsPage.submitButton);
        Assert.assertTrue(IsDisplayed(formsPage.visibleAfterSubmitForms));
        Assert.assertEquals(driver.getCurrentUrl(),practiceFormURL);
    }
    @Test (priority = 40)
    public void practiceFormMandatoryFieldsFlow () throws InterruptedException {
        String firstName = excelReader.getStringData("PracticeForms", 0, 1);
        String lastName = excelReader.getStringData("PracticeForms", 1, 1);
        String userNumber = excelReader.getStringData("PracticeForms", 2, 1);
        practiceFormCheck ();
        textBoxFieldsInputs(formsPage.firstName,firstName);
        textBoxFieldsInputs(formsPage.lastName,lastName);
        formsPage.clickOnGenderButton("Male");
        textBoxFieldsInputs(formsPage.userNumber,userNumber);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", formsPage.submitButton);
        Assert.assertTrue(IsDisplayed(formsPage.submitionForm));
        js.executeScript("arguments[0].click();", formsPage.closeButton);
        Assert.assertEquals(driver.getCurrentUrl(),practiceFormURL);
    }
    @Test (priority = 50)
    public void practiceFormFirstNameOnlyCantSubmit () throws InterruptedException {
        String firstName = excelReader.getStringData("PracticeForms", 0, 1);
        practiceFormCheck ();
        textBoxFieldsInputs(formsPage.firstName,firstName);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", formsPage.submitButton);
        Assert.assertTrue(IsDisplayed(formsPage.visibleAfterSubmitForms));
        Assert.assertEquals(driver.getCurrentUrl(),practiceFormURL);
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
