package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.pages.CshrFullJobDescriptionPage;

public class CshrFullJobDescriptionSteps {
    CshrFullJobDescriptionPage cshrFullJobDescriptionPage;

    @Step
    public void verifyApplyIsDisplayed() {
        Assert.assertTrue(cshrFullJobDescriptionPage.isApplyDisplayed());
    }

    @Step
    public void clickApply() {
        cshrFullJobDescriptionPage.clickApply();
    }

    @Step
    public void clickBackToSearchResults() {
        cshrFullJobDescriptionPage.backToSearchresults();
    }

    @Step
    public void isBackToSearchResultsDisplayed() {
        cshrFullJobDescriptionPage.isBackToSearchResultsDisplayed();
    }
}
