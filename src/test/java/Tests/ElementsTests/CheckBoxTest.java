package Tests.ElementsTests;

import Base.DemoQaBase;
import Pages.ElementsCheckBoxPage;
import Pages.ElementsPage;
import Pages.FirstPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class CheckBoxTest extends DemoQaBase {
    String titleOfTheCheckBoxPage = "Check Box";
    @BeforeMethod
    public void setUpPage () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(elementsPageURL);                                                  //every test starts from the elements page
        firstPage = new FirstPage();
        elementsPage = new ElementsPage();
        checkBoxPage = new ElementsCheckBoxPage();
    }
    @Test(priority = 10)
    public void shouldGoToCheckBoxPage()   {
        elementsPage.clickOnTheButtonFromTheElementsMenu("Check Box");
        Assert.assertTrue(getTextFromWebElement(elementsPage.titlePage).contains(titleOfTheCheckBoxPage));
        Assert.assertEquals(driver.getCurrentUrl(),checkBoxPageURL);
    }
    @Test(priority = 15)
    public void shouldGoToHomePageByClickingLogoButtonFromCheckBoxPage() {
        shouldGoToCheckBoxPage();
        firstPage.logoButton.click();                                               //when click on the logo return to the home page
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
    }
    @Test (priority = 20)
    public void shouldClickOnPlusSign () {      //test if we can expand menu
        shouldGoToCheckBoxPage();
        checkBoxPage.plusSign.click();
        Assert.assertFalse(IsDisplayed(checkBoxPage.checkBoxMenuCollapsed));
    }
    @Test (priority = 30)
    public void shouldClickOnMinusSign() {
        shouldClickOnPlusSign();                //assuming we can expand menu, test if we can collapse it
        checkBoxPage.minusSign.click();
        Assert.assertTrue(IsDisplayed(checkBoxPage.checkBoxMenuCollapsed));
    }
    @Test (priority = 40)
    public void shouldClickOnAllArrowsToExpandMenu() {          // expanding menu by clicking on arrows
//        https://www.guru99.com/scroll-up-down-selenium-webdriver.html
        shouldGoToCheckBoxPage();
        for (int i = 0; i < checkBoxPage.arrowList.size(); i++) {
            checkBoxPage.arrowList.get(i).click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }
        Assert.assertFalse(IsDisplayed(checkBoxPage.checkBoxMenuCollapsed));
        Assert.assertTrue(IsDisplayed(checkBoxPage.excelFileCheckBox));
    }
    @Test (priority = 50)
    public void shouldClickOnAllArrowsToCollapseMenu() {             // assuming we expanded menu collapse it with arrows
        shouldClickOnAllArrowsToExpandMenu();
        int listSize= checkBoxPage.arrowList.size()-1;
        for (int j = listSize; j >= 0; j--) {
            checkBoxPage.arrowList.get(j).click();
        }
        Assert.assertTrue(IsDisplayed(checkBoxPage.checkBoxMenuCollapsed));
    }
    @Test (priority = 60)
    public void shouldClickOnAllCheckBoxesFlow () {
        // don't need to check if some random boxes could be selected
        shouldClickOnPlusSign();
        for (int i = 0; i<checkBoxPage.listOfCheckBoxes.size(); i++ ) {
            checkBoxPage.listOfCheckBoxes.get(i).click();
            Assert.assertTrue(checkBoxPage.result.getText().contains("You have selected :"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            //making it ignore case letters and to delete whitespace so that titles match for all
            Assert.assertTrue(StringUtils.containsAnyIgnoreCase(StringUtils.deleteWhitespace(checkBoxPage.listOfCheckBoxes.get(i).getText()),
                    checkBoxPage.listOfTextResults.get(0).getText())); // asserting that all the checkboxes are listed in the resulting text
            checkBoxPage.listOfCheckBoxes.get(i).click();
            Assert.assertFalse(IsDisplayed(checkBoxPage.result));
        }
    }
    @Test (priority = 70)                          //maybe best for manual
    public void shouldHaveHalfCheckMarkWhenClickOnOneChild () {
        shouldClickOnPlusSign();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        checkBoxPage.excelFileCheckBox.click();
        Assert.assertTrue(checkBoxPage.result.getText().contains("You have selected :\n" + "excelFile"));
        Assert.assertTrue(IsDisplayed(checkBoxPage.downloadsCheckBoxHalfCheck)); // made my own xpath
    }
    @AfterMethod
    public void shutDownTest () {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
