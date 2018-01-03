package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.pages.CshrHomePage;

public class CshrHomePageSteps {


    CshrHomePage cshrHomePage;


    @Step
    public void openCshrSearchPage() {
        cshrHomePage.open();
    }

    @Step
    public void enterSearchKeyWord(String searchRequest) {
        cshrHomePage.searchForKeyword(searchRequest);
    }

    @Step
    public void enterSearchKeyWordAndLocation(String keyword, String location) {
        cshrHomePage.searchForKeyword(keyword);
        cshrHomePage.searchForLocation(location);
    }

    @Step
    public void clickSearch(){
        cshrHomePage.clickSearch();
    }

    @Step
    public void welshLanguageLinkCheck(){
        Assert.assertTrue(cshrHomePage.welshLanguage());
    }

}
