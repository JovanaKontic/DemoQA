package Base;

import Pages.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class DemoQaBase {

    public static WebDriver driver;
    public WebDriverWait waiter;
    public ExcelReader excelReader;

    public String homePageURL;
    public String elementsPageURL;
    public String formsPageURL;
    public String alertsPageURL;
    public String widgetsPageURL;
    public String interactionPageURL;
    public String bookStorePageURL;
    public String textBoxPageURL;
    public String brokenLinkURL;
    public String practiceFormURL;
    public String newTabAlertsURL;



    public FirstPage firstPage;
    public ElementsPage elementsPage;
    public ElementsCheckBoxPage checkBoxPage;
    public FormsPage formsPage;
    public AlertsPage alertsPage;
    public WidgetsPage widgetsPage;



    @BeforeClass
    public void startUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("src/test/java/TestDataDemoQA.xlsx");
        homePageURL = excelReader.getStringData("URL", 0, 1);
        elementsPageURL = excelReader.getStringData("URL", 1, 1);
        formsPageURL = excelReader.getStringData("URL", 2, 1);
        alertsPageURL = excelReader.getStringData("URL", 3, 1);
        widgetsPageURL = excelReader.getStringData("URL", 4, 1);
        interactionPageURL = excelReader.getStringData("URL", 5, 1);
        bookStorePageURL = excelReader.getStringData("URL", 6, 1);
        textBoxPageURL = excelReader.getStringData("URL", 7, 1);
        brokenLinkURL = excelReader.getStringData("URL", 8, 1);
        practiceFormURL = excelReader.getStringData("URL", 9, 1);
        newTabAlertsURL = excelReader.getStringData("URL", 10, 1);

    }


    public boolean IsDisplayed(WebElement element) {
//        Ova metoda funkcionise samo ako se elementi nalaze preko anotacija, ne preko getera
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {

        }
        return webelement;
    }
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForVisibility(WebElement element) {
        waiter.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        waiter.until(ExpectedConditions.elementToBeClickable(element));
    }
    public boolean IsEnabled(WebElement element) {
//        Ova metoda funkcionise samo ako se elementi nalaze preko anotacija, ne preko getera
        boolean webelement = false;
        try {
            webelement = element.isEnabled();
        } catch (Exception e) {

        }
        return webelement;
    }
    public void textBoxFieldsInputs (WebElement element, String string) {  //ZA UNOS TEXTA U TEX BOX
        element.clear();
        element.sendKeys(string);
    }
    public void doubleClickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', { bubbles: true }));", element);
    }


    @AfterClass
    public void shutDown () {
//        driver.close();
    }

}


//    public void waitElementClickable (WebElement element){
//        wdwait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    public void waitPageVisible (String url){
//        wdwait.until(ExpectedConditions.urlToBe("url"));
//    }
