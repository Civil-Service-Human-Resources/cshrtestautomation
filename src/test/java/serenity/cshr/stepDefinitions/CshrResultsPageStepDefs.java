package serenity.cshr.stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenity.cshr.steps.CshrSearchResultsSteps;

public class CshrResultsPageStepDefs {

    @Steps
    CshrSearchResultsSteps cshrSearchResultsSteps;

    @And("^partial job description$")

    public void partial_job_description() {
        cshrSearchResultsSteps.verifyJobDescriptionExists();
    }

    @Then("^I should see all the results matching \"([^\"]*)\" in a new page$")
    public void i_should_see_all_the_results_matching_in_a_new_page(String arg1) {

    }

    @Then("^include the number of jobs found, partial job description, no of vaccancies, location, salary, job grade, closing date$")
    public void include_the_number_of_jobs_found_partial_job_description_no_of_vaccancies_location_salary_job_grade_closing_date() {

    }

    @When("^I select a job that matches my criteria$")
    public void i_select_a_job_that_matches_my_criteria() {

    }

    @Then("^I should be shown a full description of the job in a new page with salary min and max, closing date$")
    public void i_should_be_shown_a_full_description_of_the_job_in_a_new_page_with_salary_min_and_max_closing_date() {

    }

    @And("number of vaccancies, location, salary, job grade, closing date")
    public void number_of_vaccancies_location_salary_jobgrade_closing_date() {

    }
}
