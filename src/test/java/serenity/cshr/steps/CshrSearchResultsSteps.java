package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.pages.CshrResultsPage;

public class CshrSearchResultsSteps {
    CshrResultsPage cshrResultsPage;
    //CshrHomePage cshrHomePage;

    @Step
    public void verifyJobDescriptionExists() {
        Assert.assertTrue(cshrResultsPage.jobDescriptionExists());
    }

    @Step
    public void noOfJobsFound() {
        System.out.println(cshrResultsPage.jobsDispalyed());
    }

    @Step
    public void noOfVacanciesFound() {
        cshrResultsPage.noOfVacanciesText();
    }

    @Step
    public void clickOnJobTitle() {
        cshrResultsPage.clickOnJobTitle();
    }

    @Step
    public void clickBackToSearch() {
        cshrResultsPage.clickBackToSearch();
    }

}
