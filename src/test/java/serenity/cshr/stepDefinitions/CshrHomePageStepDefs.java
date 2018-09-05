package serenity.cshr.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import serenity.cshr.steps.CshrHomePageSteps;

public class CshrHomePageStepDefs {

    @Steps
    CshrHomePageSteps cshrHomePageSteps;


    @Given("^I open cshr website$")
    public void i_open_cshr_website() {
        cshrHomePageSteps.openCshrSearchPage();
    }

    @Then("^I should see homepage with options to search for location and keyword$")
    public void i_should_see_homepage_with_options_to_search_for_location_and_keyword() {
        cshrHomePageSteps.areKeywordAndLocationPresent();
    }

    @When("^I enter \"([^\"]*)\" in job title and click search$")
    public void i_enter_in_job_title_and_click_search(String searchKeyword) {
        cshrHomePageSteps.enterSearchKeyWord(searchKeyword);
    }


    @When("^I click on search$")
    public void i_click_on_search(){
        cshrHomePageSteps.clickSearch();
    }

    @When("^I enter (.*) in job title and (.*) in location$")
    public void i_enter_in_job_title_and_in_location_and_click_search(String keyword, String location) {
        cshrHomePageSteps.enterSearchKeyWordAndLocation(keyword, location);
    }

   /* I enter either keyword "<keyword>" or location "<locationkeyword>"
    public void i_enter_in_job_title_and_in_location_and_click_search(String keyword, String location) {
        cshrHomePageSteps.enterSearchKeyWordAndLocation(keyword, location);
    }*/
    @Then("^I should see a link to welsh language$")
    public void I_should_see_a_link_to_welsh_language(){
        cshrHomePageSteps.welshLanguageLinkCheck();
    }

}
