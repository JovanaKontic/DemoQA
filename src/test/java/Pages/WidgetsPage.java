package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WidgetsPage extends DemoQaBase {
    public WidgetsPage() {
        PageFactory.initElements(driver,this);
    }


    public @FindBy(className ="header-text") List<WebElement> buttonsList;  // RADI NE MENJAJ
    public @FindBy( className = "main-header") WebElement titlePage;
    public @FindBy(css =".element-list.collapse.show") WebElement widgetsRollDown;
    public @FindBy(className = "text") List<WebElement> widgetsPageList;
    public @FindBy(id = "section1Heading") WebElement firstTab;
    public @FindBy(id="section1Content") WebElement firstParagraphText;
    public @FindBy(css = ".collapse.show") WebElement tabVisibility;
    public @FindBy(id = "section2Heading") WebElement secondTab;
    public @FindBy(id = "section3Heading") WebElement thirdTab;
    public @FindBy(id="section2Content") WebElement secondParagraphText;
    public @FindBy(id="section3Content") WebElement thirdParagraphText;


    public @FindBy(id="react-select-4-option-0") WebElement multiColorOptionMen;
    public @FindBy(id="autoCompleteMultipleContainer") WebElement multiColorTextBox;


    // Template za pretragu elemenata:
// @FindBy(css = "tag[atribute = 'value']")
// public WebElement element;


    //==================================================================================================================

    public void clickOnAWidgetsButton() {                        //METODA DA IZLISTA IZ GLAVNOD MENIJA (HOME PAGE) KARTICU
        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i).getText().equals("Widgets")) {
                buttonsList.get(i).click();
                break;
            }
        }
    }
    public void clickOnWidgetsMenuButton(String text) {               //METODA DA IZLISTA IZ gender butons
        for (int i = 0; i < widgetsPageList.size(); i++) {
            if (widgetsPageList.get(i).getText().equals(text)) {
                widgetsPageList.get(i).click();
                break;
            }
        }
    }


}

