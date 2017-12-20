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

}
