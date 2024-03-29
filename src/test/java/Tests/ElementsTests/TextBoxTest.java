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

public class TextBoxTest extends DemoQaBase {
    String titleOfTheTextBoxPage = "Text Box";
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(elementsPageURL);                                                  //every test starts from the elements page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    @Test(priority = 10)
    public void shouldGoToTextBoxPage() {                                           //test if the button works
        clickingOnElementInListUsingText(elementsPage.elementsPageList,"Text Box");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheTextBoxPage));
        Assert.assertEquals(driver.getCurrentUrl(),textBoxPageURL);
    }
    @Test(priority = 15)
    public void shouldGoToHomePageByClickingLogoButtonFromTextBoxPage() {
        shouldGoToTextBoxPage();
        firstPage.logoButton.click();                                               //when click on the logo return to the home page
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 20)
    public void shouldFailWithEmptyFields(){
        shouldGoToTextBoxPage();
        elementsPage.submitButton.click();
        Assert.assertFalse(elementsPage.outputMessageField.getText().contains(":"));            //FAIL = False
    }
    @Test (priority = 30)
    public void shouldInputFullName() {                                                  //test inserting text into boxes
        String fullname = excelReader.getStringData("TextBox", 0,1);
        shouldGoToTextBoxPage();
        textBoxFieldsInputs(elementsPage.fullNameField,fullname);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(fullname));
    }
    @Test (priority = 40)
    public void shouldInputEmail() {                                                     //test inserting text into boxes
        String email = excelReader.getStringData("TextBox", 1,1);
        shouldGoToTextBoxPage();
        textBoxFieldsInputs(elementsPage.emailField,email);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(email));
    }
    @Test (priority = 45)
    public void shouldNotAcceptInvalidEmail() {
        shouldGoToTextBoxPage();                                //test invalid email formats
        for (int i=1; i<=excelReader.getLastRow("TextBox"); i++) {
            String email = excelReader.getStringData("TextBox", i,2);
            textBoxFieldsInputs(elementsPage.emailField,email);
            elementsPage.submitButton.click();
            Assert.assertFalse(elementsPage.outputMessageField.getText().contains(email));  // failing to submit email in output field
        }
    }
    @Test (priority = 50)
    public void shouldInputCurrentAddress() {                                             //test inserting text into boxes
        String currentAddress = excelReader.getStringData("TextBox", 2,1);
        shouldGoToTextBoxPage();
        textBoxFieldsInputs(elementsPage.currentAddressField,currentAddress);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(currentAddress));
    }
    @Test (priority = 60)
    public void shouldInputPermanentAddress() {                                             //test inserting text into boxes
        String permanentAddress = excelReader.getStringData("TextBox", 1,1);
        shouldGoToTextBoxPage();
        textBoxFieldsInputs(elementsPage.permanentAddressField,permanentAddress);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(permanentAddress));
    }
    @Test (priority = 70)
    public void textBoxHappyFlow() {                                                                       // test happy path
        String fullname = excelReader.getStringData("TextBox", 0,1);
        String email = excelReader.getStringData("TextBox", 1,1);
        String currentAddress = excelReader.getStringData("TextBox", 2,1);
        String permanentAddress = excelReader.getStringData("TextBox", 1,1);
        shouldGoToTextBoxPage();
        textBoxFieldsInputs(elementsPage.fullNameField,fullname);
        textBoxFieldsInputs(elementsPage.emailField,email);
        textBoxFieldsInputs(elementsPage.currentAddressField,currentAddress);
        textBoxFieldsInputs(elementsPage.permanentAddressField,permanentAddress);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(fullname));
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(email));
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(currentAddress));
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(permanentAddress));
    }
    @AfterMethod
    public void shutDownTest () {
//        driver.manage().deleteAllCookies();
//        driver.close();
    }
}
