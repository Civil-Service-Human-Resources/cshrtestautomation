package serenity.cshr.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenity.cshr.steps.CshrHomePageSteps;
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

    }

    @When("^I enter \"([^\"]*)\" in job title and click search$")
    public void i_enter_in_job_title_and_click_search(String searchKeyword) {
        cshrHomePageSteps.enterSearchKeyWord(searchKeyword);
    }


    @When("^I enter \"([^\"]*)\" in location and click search$")
    public void i_enter_in_location_and_click_search(String arg1) {
    }


    @When("^I enter \"([^\"]*)\" in job title and \"([^\"]*)\" in location and click search$")
    public void i_enter_in_job_title_and_in_location_and_click_search(String keyword, String location) {
        cshrHomePageSteps.enterSearchKeyWordAndLocation(keyword, location);
    }

    //TODO
    @Then("^I should see only the results matching \"([^\"]*)\" in \"([^\"]*)\" in a new page$")
    public void i_should_see_only_the_results_matching_in_in_a_new_page(String arg1, String arg2) {
    }

    @Then("^I should see a link to welsh language$")
    public void I_should_see_a_link_to_welsh_language(){
        cshrHomePageSteps.welshLanguageLinkCheck();
    }


}
