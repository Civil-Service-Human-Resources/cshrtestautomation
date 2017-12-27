package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.pages.CshrHomePage;

public class CshrHomePageSteps {

    //CshrSearchPage searchPage;
    //CshrResultsPage resultsPage;
    CshrHomePage cshrHomePage;

  /*@Step
  public void openGoogleSearchPage() {
    searchPage.open();
  }

  @Step
  public void searchFor(String searchRequest) {
    resultsPage = searchPage.searchFor(searchRequest);
  }

  @Step
  public void verifyResult(String searchResult) {
    List<String> results = resultsPage.getResultsList();
    Assert.assertTrue(results.contains(searchResult));
  }
  @Step
  public void openSearchPage(){
    searchPage.open();
  }*/

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
        cshrHomePage.searchForKeywordAndLocation(keyword, location);
    }

    @Step
    public void welshLanguageLinkCheck(){
        Assert.assertTrue(cshrHomePage.welshLanguage());
    }

}
