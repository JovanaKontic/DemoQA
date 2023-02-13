package Tests;

import Base.DemoQaBase;
import Pages.ElementsCheckBoxPage;
import Pages.FirstPage;
import Pages.ElementsPage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;

public class ElementsPageTest extends DemoQaBase {
    @BeforeMethod
    public void setUpPage () {
//
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homePageURL);
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
        checkBoxPage = new ElementsCheckBoxPage();

    }
    public void goToElementsPage () throws InterruptedException {
        Thread.sleep(3000);
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
    }
    @Test (priority = 10)
    public void elementsCardRollUp () throws InterruptedException {
        goToElementsPage ();
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
        Thread.sleep(3000);
        elementsPage.clickOnElementsButton();
        Assert.assertFalse(IsDisplayed(elementsPage.elementsRollDown));
    }
//    ==================================================================================================================
    @Test (priority = 20)
    public void textBoxCheck () throws InterruptedException {
        goToElementsPage ();
        elementsPage.clickOnElementsMenuButton("Text Box");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 25)
    public void textBoxFlow () throws InterruptedException {
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
    public void textBoxFieldFlow () throws InterruptedException {
        String fullname = excelReader.getStringData("TextBox", 0,1);
        textBoxCheck();
        textBoxFieldsInputs(elementsPage.fullNameField,fullname);
        elementsPage.submitButton.click();
        Assert.assertTrue(elementsPage.outputMessageField.getText().contains(":"));
    }
    @Test (priority = 35)
    public void textBoxFieldsEmptyFlow () throws InterruptedException {
        textBoxCheck();
        elementsPage.submitButton.click();
        Assert.assertFalse(elementsPage.outputMessageField.getText().contains(":"));
    }
//    ==================================================================================================================
    @Test (priority = 40)
    public void checkBoxCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Check Box");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 45)
    public void checkBoxArrowsFlow () throws InterruptedException {
        checkBoxCheck();
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
    public void checkBoxesFlow () throws InterruptedException {
        checkBoxCheck();
        checkBoxPage.plusSign.click();
        checkBoxPage.homeCheckBox.click();
        Assert.assertTrue(checkBoxPage.getNotificationText().contains("home"));
        checkBoxPage.homeCheckBox.click();
        Assert.assertFalse(IsDisplayed(checkBoxPage.result));
        checkBoxPage.minusSign.click();
        Assert.assertFalse(IsDisplayed(checkBoxPage.desktopCheckBox));
    }
//    ==================================================================================================================
    @Test (priority = 60)
    public void radioButtonCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Radio Button");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
        Assert.assertTrue(IsDisplayed(elementsPage.radioButtonText));
    }
    @Test (priority = 65)
    public void radioButtonYes () throws InterruptedException {
        radioButtonCheck ();
        elementsPage.yesRadioButton.click();
        Assert.assertTrue(elementsPage.getRadioMessage().contains("Yes"));
    }
    @Test (priority = 70)
    public void radioButtonImpressive () throws InterruptedException {
        radioButtonCheck ();
        elementsPage.impressiveRadioButton.click();
        Assert.assertTrue(elementsPage.getRadioMessage().contains("Impressive"));
    }
    @Test (priority = 80)
    public void radioButtonNo () throws InterruptedException {
        radioButtonCheck ();
        String noClickPlace = elementsPage.noRadioButton.getCssValue("cursor");
        Assert.assertEquals(noClickPlace, "not-allowed");
    }
//    ==================================================================================================================
    @Test (priority = 90)
    public void webTablesCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Web Tables");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 95)
    public void webTablesAdd () throws InterruptedException {
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 100)
    public void webTablesSearch () throws InterruptedException {
        String firstName = excelReader.getStringData("WebTables", 0,1);
        webTablesCheck ();
        textBoxFieldsInputs(elementsPage.searchBoxWebTables,firstName);
        Assert.assertTrue(IsDisplayed(elementsPage.noRowMessagaWebTables));
    }
    @Test (priority = 110)
    public void webTablesFlow () throws InterruptedException {
        String firstName = excelReader.getStringData("WebTables", 0,1);
        String lastName = excelReader.getStringData("WebTables", 1,1);
        String email = excelReader.getStringData("WebTables", 2,1);
        String age = excelReader.getStringData("WebTables", 3,1);
        String salary = excelReader.getStringData("WebTables", 4,1);
        String department = excelReader.getStringData("WebTables", 5,1);
        String newFirstName = excelReader.getStringData("WebTables", 6,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();                                      //DODAJEMO NOVI UNOS
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
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
    @Test (priority = 120)
    public void webTablesInvalidRegistrationFirstnameFlow () throws InterruptedException {
        String firstName = excelReader.getStringData("WebTables", 0,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 121)
    public void webTablesInvalidRegistrationLastNameFlow () throws InterruptedException {
        String lastName = excelReader.getStringData("WebTables", 1,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 122)
    public void webTablesInvalidRegistrationEmailFlow () throws InterruptedException {
        String email = excelReader.getStringData("WebTables", 2,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.emailField, email);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 123)
    public void webTablesInvalidRegistrationAgeFlow () throws InterruptedException {
        String age = excelReader.getStringData("WebTables", 3,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 124)
    public void webTablesInvalidRegistrationSalaryFlow () throws InterruptedException {
        String salary = excelReader.getStringData("WebTables", 4,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 125)
    public void webTablesInvalidRegistrationDepartmentFlow () throws InterruptedException {
        String department = excelReader.getStringData("WebTables", 5,1);
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 126)
    public void webTablesInvalidRegistrationEmptyFlow () throws InterruptedException {
        webTablesCheck ();
        elementsPage.addWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, "");
        elementsPage.submitButton.click();
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
    }
    @Test (priority = 130)
    public void webTablesAdd3AccountsFlow () throws InterruptedException {
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

        webTablesCheck ();
        elementsPage.addWebTables.click();                                      //DODAJEMO NOVI UNOS
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName);
        textBoxFieldsInputs(elementsPage.emailField, email);
        textBoxFieldsInputs(elementsPage.ageWebTables, age);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department);
        elementsPage.submitButton.click();

        waitForVisibility( elementsPage.addWebTables);
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

        waitForVisibility( elementsPage.addWebTables);
        elementsPage.addWebTables.click();                                      //DODAJEMO NOVI UNOS
        Assert.assertTrue(IsDisplayed(elementsPage.registrationFormWebTables));
        textBoxFieldsInputs(elementsPage.firstNameWebTables, firstName2);
        textBoxFieldsInputs(elementsPage.lastNameWebTables, lastName2);
        textBoxFieldsInputs(elementsPage.emailField, email2);
        textBoxFieldsInputs(elementsPage.ageWebTables, age2);
        textBoxFieldsInputs(elementsPage.salaryWebTables, salary2);
        textBoxFieldsInputs(elementsPage.departmentWebTables, department2);
        elementsPage.submitButton.click();


        waitForVisibility(elementsPage.rowDropDownWebTables);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        elementsPage.rowDropDownWebTables.click();
        elementsPage.row5WebTables.click();
        waitForVisibility(elementsPage.nextButtonWebTables);
        elementsPage.nextButtonWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.secondPageNumberWebTables));
        elementsPage.previousButtonWebTables.click();
        Assert.assertTrue(IsDisplayed(elementsPage.firstPageNumberWebTables));
    }
    //***********************  JOS DODAJ KAD BUDES STIGLA TESTOVE ZA SORT DESC I ASC ***********************************
//    ==================================================================================================================
    @Test (priority = 140)
    public void buttonsCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Buttons");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 150)
    public void buttonsDoubleClick () throws InterruptedException {
        buttonsCheck();
        doubleClickWithJS(elementsPage.doubleClickButton);
        Assert.assertTrue(IsDisplayed(elementsPage.doubleClickMessage));
    }
    @Test (priority = 160)
    public void buttonsRightClick () throws InterruptedException {
        Actions action = new Actions(driver);
        buttonsCheck();
        action.contextClick(elementsPage.rightClickButton).perform();
        Assert.assertTrue(IsDisplayed(elementsPage.rightClickMessage));
    }
    @Test (priority = 170)
    public void buttonsJustClick () throws InterruptedException {
        buttonsCheck();
        elementsPage.clickOnJustClick();
        Assert.assertTrue(IsDisplayed(elementsPage.justClickMessage));
    }

//    ==================================================================================================================
    @Test (priority = 180)
    public void linksCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Links");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 190)
    public void linksHome () throws InterruptedException {
       linksCheck();
       String oldTab = driver.getWindowHandle();
       elementsPage.simpleLink.click();
       ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
       newTab.remove(oldTab);
       driver.switchTo().window(newTab.get(0));
       Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
    }
    @Test (priority = 200)
    public void linksHomeDynamic () throws InterruptedException {
        linksCheck();
        String oldTab = driver.getWindowHandle();
        elementsPage.dynamicLink.click();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
    }
    @Test (priority = 210)
    public void linksCreated () throws InterruptedException {
        linksCheck();
        elementsPage.createdLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("201"));
    }
    @Test (priority = 220)
    public void linksNoContent () throws InterruptedException {
        linksCheck();
        elementsPage.noContentLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("204"));
    }
    @Test (priority = 230)
    public void linksMoved () throws InterruptedException {
        linksCheck();
        elementsPage.movedLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("301"));
    }
    @Test (priority = 240)
    public void linksBadRequest () throws InterruptedException {
        linksCheck();
        elementsPage.badRequestLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("400"));
    }
    @Test (priority = 250)
    public void linksUnauthorized () throws InterruptedException {
        linksCheck();
        elementsPage.unauthorizedLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("401"));
    }
    @Test (priority = 260)
    public void linksForbidden () throws InterruptedException {
        linksCheck();
        elementsPage.forbiddenLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("403"));
    }
    @Test (priority = 270)
    public void linksNotFound () throws InterruptedException {
        linksCheck();
        elementsPage.notFoundLink.click();
        waitForVisibility(elementsPage.linkResponseLink);
        Assert.assertTrue(elementsPage.linkResponseLink.getText().contains("404"));
    }

//    ==================================================================================================================
    @Test (priority = 280)
    public void brokenLinksCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Broken Links - Images");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 290)
    public void brokenlinksValidLink () throws InterruptedException {
        brokenLinksCheck();
        elementsPage.validLink.click();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 300)
    public void brokenlinksBrokenLink() throws InterruptedException {
        brokenLinksCheck();
        elementsPage.brokenLink.click();
        Assert.assertEquals(driver.getCurrentUrl(),brokenLinkURL);
    }

//    ==================================================================================================================

    @Test (priority = 310)
    public void uploadAndDownloadCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Upload and Download");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
    @Test (priority = 320)
    public void downloadValidation () throws InterruptedException {
        uploadAndDownloadCheck();
        elementsPage.downloadButton.click();
//      KAKO ASERTOVATI DA JE FAJL SKINUT ?? LINK RESPONSE 200 ???
    }
//    @Test (priority = 330)
//    public void uploadValidation () throws InterruptedException {
//        uploadAndDownloadCheck();
//        waitForClickability(elementsPage.uploadFileButton);
//        elementsPage.uploadFileButton.click();
//        //      KAKO ASERTOVATI DA JE FAJL IZABRAN ??
//    }

//    ==================================================================================================================
    @Test (priority = 340)
    public void dynamicPropertiesCheck () throws InterruptedException {
        goToElementsPage();
        elementsPage.clickOnElementsMenuButton("Dynamic Properties");
        Assert.assertTrue(IsDisplayed(elementsPage.titlePage));
    }
//    @Test (priority = 350)
//    public void randomIDValidation () throws InterruptedException {    // KAKO DA ASERTUJEM AKO MU SE MENJA ID
//        dynamicPropertiesCheck ();
//        waitForVisibility(elementsPage.randomIDText);
//        Assert.assertTrue(elementsPage.randomIDText.getText().contains("This text has random Id"));
//    }
    @Test (priority = 350)
    public void enableAfterButtonValidation () throws InterruptedException {
        dynamicPropertiesCheck ();
        waitForClickability(elementsPage.enableAfterButton);
        Assert.assertTrue(IsEnabled(elementsPage.enableAfterButton));
    }
    @Test (priority = 360)
    public void colorChangeValidation () throws InterruptedException {
        dynamicPropertiesCheck ();
        Assert.assertFalse(IsDisplayed(elementsPage.colorChangeButton));
        waitForVisibility(elementsPage.colorChangeButton);
        Assert.assertTrue(IsDisplayed(elementsPage.colorChangeButton));
    }
    @Test (priority = 370)
    public void visibleAfterValidation () throws InterruptedException {
        dynamicPropertiesCheck ();
        Assert.assertFalse(IsDisplayed(elementsPage.visibleAfterButton));
        waitForVisibility(elementsPage.visibleAfterButton);
        Assert.assertTrue(IsDisplayed(elementsPage.visibleAfterButton));
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.quit();
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







