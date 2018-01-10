package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.Utils.DBUtils;
import serenity.cshr.pages.CshrHomePage;
import serenity.cshr.pages.CshrResultsPage;

import java.sql.SQLException;

public class CshrSearchResultsSteps {
    CshrResultsPage cshrResultsPage;
    DBUtils dbUtils;
    int keywordAndLocationCount;

    @Step
    public void verifyJobDescriptionExists() {
        Assert.assertTrue(!cshrResultsPage.jobDescriptionExists().isEmpty());
    }

    @Step
    public String noOfJobsFound() {
        return cshrResultsPage.searchResultsTotalNum();
    }

    @Step
    public void isSearchResultsCountGreaterThanZero(){
        Assert.assertTrue(!cshrResultsPage.searchResultsTotalNum().equals("0"));
    }
    @Step
    public void noOfVacanciesFoundCount() {
        Assert.assertTrue(cshrResultsPage.listOfVacanciesNumber()>0);
    }

    @Step
    public void closingDateFoundCount() {
        Assert.assertTrue(cshrResultsPage.listOfClosingDateNumber()>0);
    }

    @Step
    public void jobGradeFoundCount() {
        Assert.assertTrue(cshrResultsPage.listOfJobGradeNumber()>0);
    }

    @Step
    public void salaryFoundCount() {
        Assert.assertTrue(cshrResultsPage.listOfSalaryNumber()>0);
    }

    @Step
    public void locationFoundCount() {
        Assert.assertTrue(cshrResultsPage.listOfLocationNumber()>0);
    }

    @Step
    public void clickOnJobTitle() {
        cshrResultsPage.clickOnJobTitle();
    }

    @Step
    public void clickBackToSearch() {
        cshrResultsPage.clickBackToSearch();
    }

    @Step
    public void isSearchResultsCountZero(){
        Assert.assertTrue(cshrResultsPage.searchResultsTotalNum().equals("0"));
    }

    @Step
    public void clickTryNewSeachLink(){
        cshrResultsPage.tryNewSeach();
    }

    @Step
    public void clearExistingKeyword(){
        cshrResultsPage.clearKeyword();
    }
    @Step
    public void verifySearchFiltersIsDisplayed(){
        cshrResultsPage.isSearchFiltersDisplayed();
    }

    @Step
    public void enterKeyword(String newKeyword){
        cshrResultsPage.enterKeyword(newKeyword);
    }

    @Step
    public void refineResults(){
        cshrResultsPage.clickRefine();
    }
    @Step
    public void navigateToNext(){

        cshrResultsPage.clickNext();
    }

    @Step
    public void navigateToLastPage(){
        cshrResultsPage.clickLastPageLink();
    }
    @Step
    public void navigateToPrevious(){
        cshrResultsPage.clickPrevious();
    }

    @Step
    public boolean verifyNextIsDisplayed(){
        return cshrResultsPage.isNextDisplayed();
    }

    @Step
    public boolean verifyPrevIsDisplayed(){
        return  cshrResultsPage.isPrevDisplayed();
    }

    @Step
    public int noOfPageNumbersDisplayed(){
        return cshrResultsPage.noOfPageLinks();
    }

    @Step
    public void areTheResultsSameAsSearch(String keyword,String location){
        try {
            dbUtils = new DBUtils();
            keywordAndLocationCount= dbUtils.countSearchByKeywordAndLocation(keyword, location);
            Assert.assertEquals(keywordAndLocationCount,Integer.parseInt(cshrResultsPage.searchResultsTotalNum()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
