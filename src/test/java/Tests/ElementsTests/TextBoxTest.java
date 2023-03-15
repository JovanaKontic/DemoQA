package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TextBoxTest extends DemoQaBase {
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
    @Test(priority = 10)                                                           //test if the button works
    public void textBoxCheck () {
        elementsPage.goToElementsPage ();
        elementsPage.clickOnElementsMenuButton("Text Box");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains("Text Box"));
    }
    @Test (priority = 20)
    public void textBoxFlow () {
        String fullname = excelReader.getStringData("TextBox", 0,1);
        String email = excelReader.getStringData("TextBox", 1,1);
        String currentAddress = excelReader.getStringData("TextBox", 2,1);
        String permanentAddress = excelReader.getStringData("TextBox", 1,1);
        textBoxCheck();
        textBoxFieldsInputs(elementsPage.fullNameField,fullname);
        textBoxFieldsInputs(elementsPage.emailField,email);
        textBoxFieldsInputs(elementsPage.currentAddressField,currentAddress);
        textBoxFieldsInputs(elementsPage.permanentAddressField,permanentAddress);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(":"));
    }
    @Test (priority = 30)
    public void textBoxFieldFlow () {
        String fullname = excelReader.getStringData("TextBox", 0,1);
        textBoxCheck();
        textBoxFieldsInputs(elementsPage.fullNameField,fullname);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(":"));
    }
    @Test (priority = 35)
    public void textBoxFieldsEmptyFlow (){
        textBoxCheck();
        elementsPage.submitButton.click();
        Assert.assertFalse(elementsPage.outputMessageField.getText().contains(":"));
    }





}
