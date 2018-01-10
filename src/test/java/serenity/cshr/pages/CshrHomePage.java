package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
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

    @WhenPageOpens
    public void makeBrowserWindowFullScreen() {
        if(!getDriver().toString().contains("appium")) {
            getDriver().manage().window().setSize(dimension);
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
}
