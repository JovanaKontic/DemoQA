package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormsPage extends DemoQaBase {
    public FormsPage() {PageFactory.initElements(driver,this);
    }

    public @FindBy(className ="header-text") List<WebElement> buttonsList;  // RADI NE MENJAJ
    public @FindBy(className = "text") List<WebElement> formsPageList;
    public @FindBy( className = "main-header") WebElement titlePage;
    public @FindBy(css =".element-list.collapse.show") WebElement formsRollDown;
    public @FindBy(className ="was-validated") WebElement visibleAfterSubmitForms;
    public @FindBy(css ="button[id='submit']") WebElement submitButton;
    public @FindBy(id = "firstName") WebElement firstName;
    public @FindBy(id = "lastName") WebElement lastName;
    public @FindBy(id = "userNumber") WebElement userNumber;
    public @FindBy(css = ".custom-control.custom-radio.custom-control-inline") List<WebElement> genderButtonList;
    public @FindBy(className = "modal-content") WebElement submitionForm;
    public @FindBy(css ="button[id='closeLargeModal']") WebElement closeButton;


//======================================================================================================================

    public void clickOnFormsButton() {                         //METODA DA IZLISTA IZ GLAVNOD MENIJA (HOME PAGE) KARTICU
        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i).getText().equals("Forms")) {
                buttonsList.get(i).click();
                break;
            }
        }
    }
    public void clickOnFormsMenuButton() {                       //METODA DA IZLISTA IZ FORMS MENIJA
        for (int i = 0; i < formsPageList.size(); i++) {
            if (formsPageList.get(i).getText().equals("Practice Form")) {
                formsPageList.get(i).click();
                break;
            }
        }
    }
    public void clickOnGenderButton(String text) {               //METODA DA IZLISTA IZ gender butons
        for (int i = 0; i < genderButtonList.size(); i++) {
            if (genderButtonList.get(i).getText().equals(text)) {
                genderButtonList.get(i).click();
                break;
            }
        }
    }
}
