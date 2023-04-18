package Pages;

import Base.DemoQaBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ElementsCheckBoxPage extends DemoQaBase {
    public ElementsCheckBoxPage() {
        PageFactory.initElements(driver,this);
    }
    public @FindBy(css = "button[aria-label='Expand all']") WebElement plusSign;
    public @FindBy(css = ".rct-node.rct-node-parent.rct-node-collapsed") WebElement checkBoxMenuCollapsed;
    public @FindBy(css = "button[aria-label='Collapse all']") WebElement minusSign;
    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[3]/ol/li[2]/span/label") WebElement excelFileCheckBox;
    public @FindBy(css = ".rct-collapse.rct-collapse-btn") List<WebElement> arrowList;
    public  @FindBy (css ="span[class = 'rct-title']" ) List<WebElement> listOfCheckBoxes;
    public @FindBy(id = "result") WebElement result;
    public @FindBy (css = "span[class ='text-success']") List<WebElement> listOfTextResults;

//    public @FindBy (css = "label[for = 'tree-node-downloads']") WebElement downloadsCheckBox;

//    public @FindBy(xpath = "//*[@id=\"tree-node\"]/ol/li/ol/li[3]/span/label/span[1]/svg") WebElement downloadsCheckBoxHalfCheck;  //nije cobar lokator

//    Template za pretragu elemenata:
// @FindBy(css = "tag[atribute = 'value']")
// public WebElement element;
//
// Kada nakon inspect element pronadjemo npr.:
// <div class = 'css-1dbjc4n r-1awozwy' >
// U template ubacujemo:
// umesto "tag" - div, umesto "atribut" - class, umesto "value" - css-1dbjc4n r-1awozwy




//    ====================================================================

}
