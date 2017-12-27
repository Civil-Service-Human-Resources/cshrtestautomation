package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.pages.CshrResultsPage;

public class CshrSearchResultsSteps {
    CshrResultsPage cshrResultsPage;

    @Step
    public void verifyJobDescriptionExists() {
        Assert.assertTrue(!cshrResultsPage.jobDescriptionExists().isEmpty());
    }

    @Step
    public void noOfJobsFound() {
        System.out.println(cshrResultsPage.searchResultsTotalNum());
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

}
