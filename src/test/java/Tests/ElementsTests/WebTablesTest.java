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
import java.util.Arrays;
import java.util.List;

public class WebTablesTest extends DemoQaBase {
    public String titleOfTheWebTablesPage = "Web Tables";

    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(elementsPageURL);                                              //every test starts from the home page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    @Test(priority = 10)
    public void shouldGoToWebTablesPage() {                     //going to web tables page
        elementsPage.clickOnTheButtonFromTheElementsMenu("Web Tables");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheWebTablesPage));
    }
    @Test(priority = 15)
    public void shouldGoToHomePageByClickingLogoButton() {
        shouldGoToWebTablesPage();
        firstPage.logoButton.click();                                   //when click on the logo return to the home page
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 20)
    public void shouldClickOnAddButton() {      //checking add button to get to registration form
        shouldGoToWebTablesPage();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 20)
    public void shouldClickOnSubmitAfterFillingRegistrationFormWithValidData() {      //filling registration form with valid data
        String firstName = excelReader.getStringData("WebTables", 0,1);
        String lastName = excelReader.getStringData("WebTables", 1,1);
        String email = excelReader.getStringData("WebTables", 2,1);
        String age = excelReader.getStringData("WebTables", 3,1);
        String salary = excelReader.getStringData("WebTables", 4,1);
        String department = excelReader.getStringData("WebTables", 5,1);
        shouldClickOnAddButton();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.emailField, email);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();
        Assert.assertFalse(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 30)
    public void shouldVerifyThatCantSubmitEmptyForm() {
        shouldClickOnAddButton();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    //shouldVerifyThatSearchBoxWorkWithValidData
    //shouldEditTable
    //shouldDeleteFromTable






    @Test (priority = 50)
    public void shouldVerifyThatCantSubmitWithFirstNameOnly () {
        String firstName = excelReader.getStringData("WebTables", 0, 1);
        shouldClickOnAddButton();
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }


    @Test (priority = 40)
    public void shouldVerifyThatSearchBoxWorkWithInvalidData () {
        String firstName = excelReader.getStringData("WebTables", 0,1);
        shouldGoToWebTablesPage ();
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,firstName);
        Assert.assertTrue(IsDisplayed(elementsPage.noRowMessagaWebTables)); //"No rows found" MESSAGE
    }
    //////////////////NAPRAVI PETLJU DA KROZ LISTU ELEMENATA U FORMULARU UBACIS PO 1 ELEMENT I KLIKNES SUBMIT
//    @Test (priority = 50)
//    public void NAPRAVI PETLJU DA KROZ LISTU ELEMENATA U FORMULARU UBACIS PO 1 ELEMENT I KLIKNES SUBMIT () throws InterruptedException {
//        String firstName = excelReader.getStringData("WebTables", 0, 1);
//        String lastName = excelReader.getStringData("WebTables", 1, 1);
//        String email = excelReader.getStringData("WebTables", 2, 1);
//        String age = excelReader.getStringData("WebTables", 3, 1);
//        String salary = excelReader.getStringData("WebTables", 4, 1);
//        String department = excelReader.getStringData("WebTables", 5, 1);
//        String[] entryList = {firstName, lastName, email, age, salary, department};
//        List<String> entryList1 = Arrays.asList(entryList);
//        shouldClickOnAddButton();
//        for (int i = 0; i < elementsPage.registrationFormTextBoxList.size(); i++) {
//            Thread.sleep(2000);
//            textBoxFieldsInputs(elementsPage.registrationFormTextBoxList.get(i), entryList1.get(i));
//            elementsPage.submitButton.click();
//            Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
//            elementsPage.registrationFormTextBoxList.get(i).clear();
//        }
//    }

















    @Test (priority = 110)
    public void webTablesFlow () {
        // input VALID data, search through accounts to find the one we made, change the account data,
        // check if the change took, erasing account, check if the account is erased
        // magnifier button doesn't work ???
        String firstName = excelReader.getStringData("WebTables", 0,1);
        String lastName = excelReader.getStringData("WebTables", 1,1);
        String email = excelReader.getStringData("WebTables", 2,1);
        String age = excelReader.getStringData("WebTables", 3,1);
        String salary = excelReader.getStringData("WebTables", 4,1);
        String department = excelReader.getStringData("WebTables", 5,1);
        String newFirstName = excelReader.getStringData("WebTables", 6,1);
        shouldClickOnAddButton();                                           //POCETAK DODAVANJA NALOGA
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.emailField, email);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,firstName);            //PRETRAGA UNOSA
        Assert.assertTrue(elementsPage.checkWebTable(firstName));
        elementsPage.editButtonWebTables.click();                               //PROMENA UNOSA
        textBoxFieldsInputs(elementsPage.firstNameWebTables, newFirstName );
        elementsPage.submitButton.click();
        elementsPage.checkWebTable(newFirstName);                               //PROVERA DA LI JE PROMENJENO
        elementsPage.deleteButtonWebTable.click();                              //BRISANJE UNOSA
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,newFirstName);      //PROVERA DA LI JE UNOS OBRISAN
        elementsPage.magnifier.click();
        Assert.assertTrue(IsDisplayed(elementsPage.noRowMessagaWebTables));
    }

    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }


}
