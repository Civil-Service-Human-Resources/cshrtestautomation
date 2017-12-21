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

    private Dimension dimension = new Dimension(1368, 768);

    @FindBy(id = "keyword")
    private WebElement keyword;

    @FindBy(id = "location")
    private WebElement location;


    @WhenPageOpens
    public void makeBrowserWindowFullScreen() {
        getDriver().manage().window().setSize(dimension);
    }

    public CshrResultsPage searchForKeyword(String searchRequest) {
        element(keyword).clear();
        element(keyword).typeAndEnter(searchRequest);
        return new CshrResultsPage(getDriver());
    }

    public CshrResultsPage searchForLocation(String searchRequest) {
        element(location).clear();
        element(location).typeAndEnter(searchRequest);
        return new CshrResultsPage(getDriver());
    }

    public CshrResultsPage searchForKeywordAndLocation(String searchKeyword, String searchlocation) {
        element(keyword).clear();
        element(keyword).typeAndEnter(searchKeyword);
        element(location).clear();
        element(location).typeAndEnter(searchlocation);
        return new CshrResultsPage(getDriver());
    }


}
