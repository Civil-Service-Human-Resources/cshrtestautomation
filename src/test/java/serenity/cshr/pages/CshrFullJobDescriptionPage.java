package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CshrFullJobDescriptionPage extends PageObject {
    public CshrFullJobDescriptionPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "button")
    private WebElement apply;

    @FindBy(linkText = "Back to search results")
    private WebElement backTOSearchResults;

    @FindBy(css = "p[style]")
    private WebElement workingHours;

    @FindBy(css = "[aria-label =Name]")
    private WebElement contactName;

    public boolean isApplyDisplayed() {
        return apply.isDisplayed();
    }

    public void clickApply() {
        apply.click();
    }

    public void findApplyText() {
        apply.getText();
    }

    public void backToSearchresults() {
        backTOSearchResults.click();
    }

    public void isBackToSearchResultsDisplayed() {
        backTOSearchResults.isDisplayed();
    }

    public boolean isContactPointDisplayed(){
       return contactName.isDisplayed();
    }

    public boolean isWorkingHoursDisplayed(){
        return workingHours.isDisplayed();
    }
}
