package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:3000/")
public class CshrHomePage extends PageObject {
    public CshrHomePage(WebDriver driver) {
        super(driver);
    }

    private Dimension dimension = new Dimension(1920, 1080);

    @FindBy(id = "keyword")
    private WebElement keyword;

    @FindBy(id = "location")
    private WebElement location;

    @FindBy(linkText = "Cymraeg")
    private WebElement welshLanguageLink;

    @FindBy(className = "button")
    private WebElement searchButton;

    @FindBy(className = "error-message")
    private WebElement locationErrorMessage;

    @FindBy(id = "search-form-errors")
    private WebElement summaryErrorMessageTitle;

    @FindBy(className = "error-summary-list")
    private WebElement errorSummaryList;

    @FindBy(className = "error-message")
    private WebElement inLineLocationError;

    @WhenPageOpens
    public void makeBrowserWindowFullScreen() {
        if(!getDriver().toString().contains("appium")) {
            getDriver().manage().window().fullscreen();
        }
    }

    public void searchForKeyword(String searchRequest) {
        element(keyword).clear();
        element(keyword).type(searchRequest);

    }

    public void searchForLocation(String searchLocation) {
        element(location).clear();
        element(location).type(searchLocation);

    }

    public CshrResultsPage clickSearch(){
        element(searchButton).click();
        return new CshrResultsPage(getDriver());
    }

    public boolean welshLanguage(){
       return welshLanguageLink.isDisplayed();
    }

    public boolean isKeywordPresent(){
        return element(keyword).isDisplayed();
    }

    public boolean isLocationDisplayed(){
        return element(location).isDisplayed();
    }

    public String getErrorSummaryMessage(){
        summaryErrorMessageTitle.getText();
        if(inLineLocationError.getText().equals(errorSummaryList.getText())){
            return errorSummaryList.getText();
        }
        else{
            return null;
        }
    }
}
