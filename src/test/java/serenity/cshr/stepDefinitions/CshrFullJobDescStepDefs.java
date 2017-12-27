package serenity.cshr.stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenity.cshr.steps.CshrFullJobDescriptionSteps;

public class CshrFullJobDescStepDefs {

    @Steps
    CshrFullJobDescriptionSteps cshrFullJobDescriptionSteps;

    @Then("^I should be shown a full description of the job in a new page with salary min and max, closing date, Apply$")
    public void i_should_be_shown_a_full_description_of_the_job_in_a_new_page_with_salary_min_and_max_closing_date_apply() {
        cshrFullJobDescriptionSteps.verifyApplyIsDisplayed();
        cshrFullJobDescriptionSteps.clickApply();
    }

    @And("^I click back to search results$")
    public void I_click_back_to_search_results() {
        cshrFullJobDescriptionSteps.clickBackToSearchResults();
    }

    @Then("^I should see back to search results$")
    public void i_should_see_back_to_search_results() {
        cshrFullJobDescriptionSteps.isBackToSearchResultsDisplayed();
    }

    @When("^I click the apply button$")
    public void i_click_the_apply_button(){
        cshrFullJobDescriptionSteps.clickApply();
    }

    //TODO
    @Then("^I should see a temporary page$")
    public void i_should_see_a_temporary_page() {

    }


}
