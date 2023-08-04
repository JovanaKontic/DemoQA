package Pages;

import Base.DemoQaBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class FirstPage extends DemoQaBase {
    public FirstPage() {
        PageFactory.initElements(driver, this);
    }

    public @FindBy(css =  ".card.mt-4.top-card") List<WebElement> cardList;
    public @FindBy(css = "img[src='/images/Toolsqa.jpg']") WebElement logoButton;

//======================================================
    public void clickOnFormsCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getText().equals("Forms")) {
                cardList.get(i).click();
                break;
            }
        }
    }
    public void clickOnAlertsCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getText().equals("Alerts, Frame & Windows")) {
                cardList.get(i).click();
                break;
            }
        }
    }
    public void clickOnWidgetsCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getText().equals("Widgets")) {
                cardList.get(i).click();
                break;
            }
        }
    }
    public void clickOnInteractionsCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getText().equals("Interactions")) {
                cardList.get(i).click();
                break;
            }
        }
    }
    public void clickOnBookstoreCard() {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getText().equals("Book Store Application")) {
                cardList.get(i).click();
                break;
            }
        }
    }
}
