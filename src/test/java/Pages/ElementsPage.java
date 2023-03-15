package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ElementsPage extends DemoQaBase {
    public ElementsPage() { PageFactory.initElements(driver,this);
    }
    public @FindBy(className ="header-text") List<WebElement> buttonsList;  // RADI NE MENJAJ
    public @FindBy(css =".element-list.collapse.show") WebElement elementsRollDown;
    public @FindBy( className = "main-header") WebElement titlePage;
    public  @FindBy (css = "path[d = 'M3 19h18v2H3v-2zM13 5.828V17h-2V5.828L4.929 11.9l-1.414-1.414L12 2l8.485 8.485-1.414 1.414L13 5.83z']") WebElement arrowUp;
    public  @FindBy (css = "path[d = 'M3 19h18v2H3v-2zm10-5.828L19.071 7.1l1.414 1.414L12 17 3.515 8.515 4.929 7.1 11 13.17V2h2v11.172z']") WebElement arrowDown;
    public @FindBy(className = "text") List<WebElement> elementsPageList;
    public @FindBy(id = "submit") WebElement submitButton;
    public @FindBy(id = "userName") WebElement fullNameField;
    public @FindBy(id = "userEmail") WebElement emailField;
    public @FindBy(id = "currentAddress") WebElement currentAddressField;
    public @FindBy(id = "permanentAddress") WebElement permanentAddressField;
    public @FindBy(id = "output") WebElement outputMessageField;

    public @FindBy(className = "mb-3") WebElement radioButtonText;
    public @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[2]/label") WebElement yesRadioButton;
    public @FindBy(className = "mt-3") WebElement radioButtonMessage;
    public @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/label") WebElement impressiveRadioButton;
    public @FindBy(css = ".custom-control.disabled.custom-radio.custom-control-inline") WebElement noRadioButton;

    public @FindBy(id = "addNewRecordButton") WebElement addWebTables;
    public @FindBy(className = "modal-content") WebElement registrationFormWebTables;
    public @FindBy(id = "firstName") WebElement firstNameWebTables;
    public @FindBy(id = "lastName") WebElement lastNameWebTables;
    public @FindBy(id = "age") WebElement ageWebTables;
    public @FindBy(id = "salary") WebElement salaryWebTables;
    public @FindBy(id = "department") WebElement departmentWebTables;
    public @FindBy(id = "searchBox") WebElement searchBoxWebTables;
    public @FindBy(className = "rt-noData") WebElement noRowMessagaWebTables;
    public @FindBy(className = "rt-tbody") List <WebElement> contentWebTable;
    public @FindBy(id = "edit-record-4") WebElement editButtonWebTables;
    public @FindBy(id = "delete-record-4") WebElement deleteButtonWebTable;
    public @FindBy(className = "input-group-append") WebElement magnifier;
    public @FindBy(css = "option[value ='5']") WebElement row5WebTables;
    public @FindBy(css = ".select-wrap.-pageSizeOptions") WebElement rowDropDownWebTables;
    public @FindBy(className = "-next") WebElement nextButtonWebTables;
    public @FindBy(css = "input[value = '2']") WebElement secondPageNumberWebTables;
    public @FindBy(className = "-previous") WebElement previousButtonWebTables;
    public @FindBy(css = "input[value = '1']") WebElement firstPageNumberWebTables;

    public @FindBy(id = "doubleClickBtn") WebElement doubleClickButton;
    public @FindBy(id = "doubleClickMessage") WebElement doubleClickMessage;
    public @FindBy(id = "rightClickBtn") WebElement rightClickButton;
    public @FindBy(id = "rightClickMessage") WebElement rightClickMessage;
    public @FindBy(css = ".btn.btn-primary") List<WebElement> justClickButton;
    public @FindBy(id = "dynamicClickMessage") WebElement justClickMessage;

    public @FindBy(id = "simpleLink") WebElement simpleLink;
    public @FindBy(id = "dynamicLink") WebElement dynamicLink;
    public @FindBy(xpath = "//*[@id=\"linkResponse\"]/b[1]") WebElement linkResponseLink;
    public @FindBy(id = "created") WebElement createdLink;
    public @FindBy(id = "no-content") WebElement noContentLink;
    public @FindBy(id = "moved") WebElement movedLink;
    public @FindBy(id = "bad-request") WebElement badRequestLink;
    public @FindBy(id = "unauthorized") WebElement unauthorizedLink;
    public @FindBy(id = "forbidden") WebElement forbiddenLink;
    public @FindBy(id = "invalid-url") WebElement notFoundLink;

    public @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/a[1]") WebElement validLink;
    public @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/a[2]") WebElement brokenLink;

    public @FindBy(id = "downloadButton") WebElement downloadButton;
    public @FindBy(id = "uploadFile") WebElement uploadFileButton;

    public @FindBy(xpath = "//*[@id=\"ZlP31\"]") WebElement randomIDText;
    public @FindBy(id = "enableAfter") WebElement enableAfterButton;
    public @FindBy(css = ".mt-4.text-danger.btn.btn-primary") WebElement colorChangeButton;
    public @FindBy(id = "visibleAfter") WebElement visibleAfterButton;


//======================================================================================================================
    public void goToElementsPage () {                                               //getting to the elements page by clicking on the elements card on the home page
        firstPage.clickOnElementsCard();
        Assert.assertEquals(driver.getCurrentUrl(),elementsPageURL);
}
    public void clickOnElementsButton() { //METODA DA IZLISTA IZ GLAVNOD MENIJA (HOME PAGE) KARTICU
        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i).getText().equals("Elements")) {
                buttonsList.get(i).click();
                break;
            }
        }
    }
    public void clickOnElementsMenuButton(String text) {     //METODA DA IZLISTA IZ ELEMENTS MENIJA BUTTON PO IMENU (TEXT)
        for (int i = 0; i < elementsPageList.size(); i++) {
            if (elementsPageList.get(i).getText().equals(text)) {
                elementsPageList.get(i).click();
                break;
            }
        }
    }
    public boolean checkWebTable(String string) {                //DA LI SADRZI STRING IZ PARAMETRA
        for (int i = 0; i < contentWebTable.size(); i++) {
            if (contentWebTable.get(i).getText().contains(string)) {
                contentWebTable.get(i).click();
                return true;
            }
        }
        return false;
    }
    public void clickOnJustClick() {     //ZA JUST CLICK
        for (int i = 0; i < justClickButton.size(); i++) {
            if (justClickButton.get(i).getText().equals("Click Me")) {
                justClickButton.get(i).click();
                break;
            }
        }
    }
}
//    public void insertDateOfBirth(String date) {
//        DateOfBirthField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
//        DateOfBirthField.sendKeys(date);
//    }

// Template za pretragu elemenata:
// @FindBy(css = "tag[atribute = 'value']")
// public WebElement element;
//
// Kada nakon inspect element pronadjemo npr.:
// <div class = 'css-1dbjc4n r-1awozwy' >
// U template ubacujemo:
// umesto "tag" - div, umesto "atribut" - class, umesto "value" - css-1dbjc4n r-1awozwy