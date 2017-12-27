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
        cshrSearchResultsSteps.clickOnJobTitle();
    }


    @And("number of vaccancies, location, salary, job grade, closing date")
    public void number_of_vaccancies_location_salary_jobgrade_closing_date() {
        cshrSearchResultsSteps.noOfVacanciesFoundCount();
        cshrSearchResultsSteps.locationFoundCount();
        cshrSearchResultsSteps.closingDateFoundCount();
        cshrSearchResultsSteps.jobGradeFoundCount();
        cshrSearchResultsSteps.salaryFoundCount();

    }

    @Then("^total number of jobs matching search$")
    public void total_number_of_jobs_matching_search() {
        cshrSearchResultsSteps.noOfJobsFound();
        cshrSearchResultsSteps.isSearchResultsCountGreaterThanZero();
    }

    @And("^I click back to search$")
    public void I_click_back_to_search() {
        cshrSearchResultsSteps.clickBackToSearch();
    }

    @Then("^I should see no results matching your search and link to navigate to home page$")
    public void i_should_see_no_results_matching_your_search_and_link_to_navigate_to_home_page() {
        cshrSearchResultsSteps.isSearchResultsCountZero();
    }


    @When("^I click the link to try a new search$")
    public void i_click_the_link_to_try_a_new_search() {
        cshrSearchResultsSteps.clickTryNewSeachLink();
    }
}
