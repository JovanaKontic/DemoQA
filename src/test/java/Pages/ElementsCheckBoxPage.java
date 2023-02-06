package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElementsCheckBoxPage extends DemoQaBase {
    public ElementsCheckBoxPage() {
        PageFactory.initElements(driver,this);
    }
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/span/label") WebElement homeCheckBox;
    public  @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/span/button") WebElement homeArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[1]/span/label/span[3]") WebElement desktopCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[1]/span/button") WebElement desktopArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[1]/ol/li[1]/span/label") WebElement notesCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/span/button") WebElement documentsArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[1]/span/label") WebElement workspaceCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[1]/span/button") WebElement workspaceArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[1]/ol/li[1]/span/label") WebElement reactCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[2]/span/button") WebElement officeArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[2]/ol/li[1]/span/label") WebElement publicCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[3]/span/button") WebElement downloadsArrow;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[3]/ol/li[1]/span/label") WebElement wordFileCheckBox;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/div/button[1]") WebElement plusSign;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/div/button[2]") WebElement minusSign;
    public @FindBy(id = "result") WebElement result;
//    ====================================================================


    public String getNotificationText() {
        return result.getText();
    }


}
