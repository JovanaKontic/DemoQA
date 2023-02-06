package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AlertsPage extends DemoQaBase {
    public AlertsPage() {
        PageFactory.initElements(driver,this);
    }

    public @FindBy(className ="header-text") List<WebElement> buttonsList;  // RADI NE MENJAJ
    public @FindBy( className = "main-header") WebElement titlePage;
    public @FindBy(css =".element-list.collapse.show") WebElement alertsRollDown;
    public @FindBy(className = "text") List<WebElement> alertsPageList;
    public @FindBy(css ="button[id='tabButton']") WebElement newTabButton;
    public @FindBy(css ="button[id='windowButton']") WebElement newWindowButton;
    public @FindBy(css ="button[id='messageWindowButton']") WebElement newWindowMessageButton;
    public @FindBy(xpath = "/html/body/text()") WebElement newWindowMessageText;
    public @FindBy(id = "alertButton") WebElement alertButton;
    public @FindBy(id = "timerAlertButton") WebElement alertWaitButton;
    public @FindBy(id = "confirmButton") WebElement confirmBoxButton;
    public @FindBy(id = "confirmResult") WebElement confirmMessage;
    public @FindBy(id = "promtButton") WebElement promptBox;
    public @FindBy(id = "promptResult") WebElement promptMessage;

    public @FindBy(id = "frame1") WebElement iFrame1;
    public @FindBy(id = "sampleHeading") WebElement iFrameText;
    public @FindBy(id = "frame2") WebElement iFrame2;

    public @FindBy(tagName = "body") WebElement iFrameParentText;

    public @FindBy(id = "showSmallModal") WebElement smallModalButton;
    public @FindBy(className = "modal-content") WebElement modalWindow;
    public @FindBy(id = "closeSmallModal") WebElement closeSmallModalButton;
    public @FindBy(className = "close") WebElement closeXModalButton;
    public @FindBy(id = "showLargeModal") WebElement largeModalButton;
    public @FindBy(id = "closeLargeModal") WebElement closeLargeModalButton;









    // Template za pretragu elemenata:
// @FindBy(css = "tag[atribute = 'value']")
// public WebElement element;

//======================================================================================================================

    public void clickOnAlertsButton() {                        //METODA DA IZLISTA IZ GLAVNOD MENIJA (HOME PAGE) KARTICU
        for (int i = 0; i < buttonsList.size(); i++) {
            if (buttonsList.get(i).getText().equals("Alerts, Frame & Windows")) {
                buttonsList.get(i).click();
                break;
            }
        }
    }
    public void clickOnAlertsMenuButton(String text) {               //METODA DA IZLISTA IZ gender butons
        for (int i = 0; i < alertsPageList.size(); i++) {
            if (alertsPageList.get(i).getText().equals(text)) {
                alertsPageList.get(i).click();
                break;
            }
        }
    }

}
