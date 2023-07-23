package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class WebTablesTest extends DemoQaBase {
    String titleOfTheWebTablesPage = "Web Tables";

    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.manage().window().maximize();
        driver.get(elementsPageURL);                                              //every test starts from the elements page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
    }
    @Test(priority = 10)
    public void shouldGoToWebTablesPage() {                     //going to web tables page
        elementsPage.clickOnTheButtonFromTheElementsMenu("Web Tables");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheWebTablesPage));
        Assert.assertEquals(driver.getCurrentUrl(),webTableURL);
    }
    @Test(priority = 15)
    public void shouldGoToHomePageByClickingLogoButtonFromWebTablesPage() {
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
    @Test (priority = 30)
    public void shouldClickCnCloseButtonOnRegistrationForm() {      //checking X button on registration form
        shouldGoToWebTablesPage();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        elementsPage.closeButtonOnRegistrationForm.click();
        waitForInvisibility(elementsPage.registrationFormWebTables);
        Assert.assertFalse(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 40)
    public void shouldClickOnSubmitAfterFillingRegistrationFormWithValidData() {      //submitting registration form with valid data
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
        waitForInvisibility(elementsPage.registrationFormWebTables);
        Assert.assertFalse(IsDisplayed(elementsPage.registrationFormWebTables));
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(email));
    }
    @Test (priority = 50)
    public void shouldVerifyThatCantSubmitEmptyForm() {                 //cant submit empty form
        shouldClickOnAddButton();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 60)
    public void shouldVerifyThatCantSubmitUncompletedForm() {            //all fields should be filled so that form could be submitted
        //probaj i sa dictionary
        String firstName = excelReader.getStringData("WebTables", 0, 1);
        String lastName = excelReader.getStringData("WebTables", 1, 1);
        String email = excelReader.getStringData("WebTables", 2, 1);
        String age = excelReader.getStringData("WebTables", 3, 1);
        String salary = excelReader.getStringData("WebTables", 4, 1);
        String department = excelReader.getStringData("WebTables", 5, 1);
        String[] entryList = {firstName, lastName, email, age, salary, department};
        List<String> entryList1 = Arrays.asList(entryList);
        for (int i = 0; i < entryList1.size(); i++) {
            shouldClickOnAddButton();
            textBoxFieldsInputs(elementsPage.registrationTextFieldsList.get(i), entryList1.get(i));
            elementsPage.submitButton.click();
            Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
            elementsPage.closeButtonOnRegistrationForm.click();
            waitForInvisibility(elementsPage.registrationFormWebTables);
            Assert.assertFalse(IsDisplayed(elementsPage.registrationFormWebTables));
        }
    }
    @Test (priority = 70)
    public void shouldFailSubmittingFormWithInvalidData() {      //submitting registration form with invalid data
        String firstName = excelReader.getStringData("WebTables", 0,4);
        String lastName = excelReader.getStringData("WebTables", 1,4);
        String email = excelReader.getStringData("WebTables", 2,4);
        String age = excelReader.getStringData("WebTables", 3,4);
        String salary = excelReader.getStringData("WebTables", 4,4);
        String department = excelReader.getStringData("WebTables", 5,4);
        shouldClickOnAddButton();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.emailField, email);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 80)
    public void shouldFailSubmittingFormWithExceedingLength() {      //submitting registration form with invalid data
        //test should fail because the inputs are too large but the form is making them short
        String firstName = excelReader.getStringData("WebTables", 0,5);
        String lastName = excelReader.getStringData("WebTables", 1,5);
        String age = excelReader.getStringData("WebTables", 3,5);
        String salary = excelReader.getStringData("WebTables", 4,5);
        String department = excelReader.getStringData("WebTables", 5,5);
        shouldClickOnAddButton();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        Assert.assertFalse(elementsPage.firstNameWebTables.getText().contains(firstName));
        Assert.assertFalse(elementsPage.lastNameWebTables.getText().contains(lastName));
        Assert.assertFalse(elementsPage.ageWebTables.getText().contains(age));
        Assert.assertFalse(elementsPage.salaryWebTables.getText().contains(salary));
        Assert.assertFalse(elementsPage.departmentWebTables.getText().contains(department));
    }
    @Test (priority = 90)
    public void shouldVerifyThatSearchBoxWorkWithInvalidData () {
        String firstName = excelReader.getStringData("WebTables", 0,1);
        shouldGoToWebTablesPage ();
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,firstName);
        Assert.assertTrue(IsDisplayed(elementsPage.noRowMessageWebTables)); //"No rows found" MESSAGE
    }
    @Test (priority = 100)
    public void shouldVerifyThatSearchBoxWorkWithValidData () {
        String name = "Cierra";
        shouldGoToWebTablesPage ();
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,name);
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(name));
    }
    @Test(priority = 110)
    public void shouldClickOnEditButton() {    //checking edit buttons
        shouldGoToWebTablesPage ();
        elementsPage.editButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test(priority = 120)
    public void shouldEditFields () {
        String email = excelReader.getStringData("WebTables", 2, 1);
        shouldClickOnEditButton();
        textBoxFieldsInputs(elementsPage.emailField, email);
        elementsPage.submitButton.click();
        waitForInvisibility(elementsPage.registrationFormWebTables);
        Assert.assertFalse(IsDisplayed(elementsPage.registrationFormWebTables));
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(email));
    }
    @Test(priority = 130)
    public void shouldDeleteFromTable() {
        String name = "Cierra";
        shouldGoToWebTablesPage ();
        elementsPage.deleteButton.click();
        Assert.assertFalse(elementsPage.checkInWebTableIfStringIsPresent(name));
    }
    @Test(priority = 140)
    public void shouldChangeRowNumbersDisplayed() {
        String rowNumber = "5 rows";
        shouldGoToWebTablesPage ();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        elementsPage.rowDropDownWebTables.click();
        elementsPage.row5WebTables.click();
        Assert.assertTrue(getTextFromWebElement(elementsPage.rowDropDownWebTables).contains(rowNumber));
        //assert da  "wholeWebTable"  ima velicinu 5
    }
    @Test (priority = 150)
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
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(firstName));
        elementsPage.editButtonWebTables.click();                               //PROMENA UNOSA
        textBoxFieldsInputs(elementsPage.firstNameWebTables, newFirstName );
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(newFirstName));                               //PROVERA DA LI JE PROMENJENO
        elementsPage.deleteButtonWebTable.click();                              //BRISANJE UNOSA
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,newFirstName);      //PROVERA DA LI JE UNOS OBRISAN
        elementsPage.magnifier.click();
        Assert.assertTrue(IsDisplayed(elementsPage.noRowMessageWebTables));
        /*
        ne postoji nalog zato sto je obrisan
         */
    }
    @Test (priority = 160)
    public void webTablesAdd3Accounts ()  {
// Checking if we can create 3 new accounts so that we can check if the next and previous buttons work latter
        String firstName = excelReader.getStringData("WebTables", 0, 1);
        String lastName = excelReader.getStringData("WebTables", 1, 1);
        String email = excelReader.getStringData("WebTables", 2, 1);
        String age = excelReader.getStringData("WebTables", 3, 1);
        String salary = excelReader.getStringData("WebTables", 4, 1);
        String department = excelReader.getStringData("WebTables", 5, 1);

        String firstName1 = excelReader.getStringData("WebTables", 0, 2);
        String lastName1 = excelReader.getStringData("WebTables", 1, 2);
        String email1 = excelReader.getStringData("WebTables", 2, 2);
        String age1 = excelReader.getStringData("WebTables", 3, 2);
        String salary1 = excelReader.getStringData("WebTables", 4, 2);
        String department1 = excelReader.getStringData("WebTables", 5, 2);

        String firstName2 = excelReader.getStringData("WebTables", 0, 3);
        String lastName2 = excelReader.getStringData("WebTables", 1, 3);
        String email2 = excelReader.getStringData("WebTables", 2, 3);
        String age2 = excelReader.getStringData("WebTables", 3, 3);
        String salary2 = excelReader.getStringData("WebTables", 4, 3);
        String department2 = excelReader.getStringData("WebTables", 5, 3);

        shouldClickOnAddButton();                                        //DODAJEMO NOVI UNOS
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.emailField, email);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();

        waitForClickability( elementsPage.addWebTables);
        elementsPage.addWebTables.click();                                      //DODAJEMO NOVI UNOS
        waitForVisibility(elementsPage.registrationFormWebTables);
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName1);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName1);
        textBoxFieldsInputs(elementsPage.emailField, email1);
        textBoxFieldsInputs(elementsPage.ageWebTables, age1);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary1);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department1);
        elementsPage.submitButton.click();

        waitForClickability( elementsPage.addWebTables);
        elementsPage.addWebTables.click();                                      //DODAJEMO NOVI UNOS
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName2);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName2);
        textBoxFieldsInputs(elementsPage.emailField, email2);
        textBoxFieldsInputs(elementsPage.ageWebTables, age2);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary2);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department2);
        elementsPage.submitButton.click();
        /*
        asserting if all 3 accounts are present
         */
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(firstName));
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(firstName1));
        Assert.assertTrue(elementsPage.checkInWebTableIfStringIsPresent(firstName2));
    }
        @Test (priority = 170)
        public void shouldClickOnNextButton()  {
        webTablesAdd3Accounts ();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        elementsPage.rowDropDownWebTables.click();
        elementsPage.row5WebTables.click();
        waitForVisibility(elementsPage.nextButtonWebTables);
        elementsPage.nextButtonWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.secondPageNumberWebTables));

    }
    @Test (priority = 180)
    public void shouldClickOnPreviousButton()  {
        shouldClickOnNextButton();
        elementsPage.previousButtonWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.firstPageNumberWebTables));
    }
    @Test (priority = 190)
    public void shouldClickOnFirstNameTabToOrderByNameAsc()  {
        shouldGoToWebTablesPage();
        Assert.assertTrue(getTextFromWebElement(elementsPage.firstCellOnTable).equalsIgnoreCase("Cierra"));
        elementsPage.clickOnTableTabsOnWebTables("First Name");
        Assert.assertTrue(getTextFromWebElement(elementsPage.firstCellOnTable).equalsIgnoreCase("Alden"));
        /*
        Alden
         */
    }
    @Test (priority = 200)
    public void shouldClickOnFirstNameTabToOrderByNameDesc()  {
        shouldClickOnFirstNameTabToOrderByNameAsc();
        elementsPage.clickOnTableTabsOnWebTables("First Name");
        Assert.assertTrue(getTextFromWebElement(elementsPage.firstCellOnTable).equalsIgnoreCase("Kierra"));
    }

/*
FOR THE PURPOSE OF THIS PROJECT IT IS REDUNDANT TO WRITE TESTS
FOR ALL TABS TO CHECK IF THEY ARE ALSO ABLE TO
CHANGE THE ORDER OF ITEMS IN A COLUMN
 */

    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
