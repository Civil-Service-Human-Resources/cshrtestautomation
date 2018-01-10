package serenity.cshr.stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import serenity.cshr.steps.CshrSearchResultsSteps;

public class CshrResultsPageStepDefs {

    int totalJobsFound;
    int totalResultsDefined;
    @Steps
    CshrSearchResultsSteps cshrSearchResultsSteps;

    @And("^partial job description$")

    public void partial_job_description() {
        cshrSearchResultsSteps.verifyJobDescriptionExists();
    }

    @Then("^I should see all the results matching \"([^\"]*)\" in a new page$")
    public void i_should_see_all_the_results_matching_in_a_new_page(String arg1) {

    }

    @Then("^I should see only the results matching \"([^\"]*)\" in \"([^\"]*)\" in a new page$")
    public void i_should_see_only_the_results_matching_in_in_a_new_page(String keyword, String location) {
        //Look for location in the html to see if all the locations are filtered according to the search string and compare it against the query
        cshrSearchResultsSteps.areTheResultsSameAsSearch(keyword,location);
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

    @When("^I clear existing search text in keyword$")
    public void Iclear_existing_search_text_in_keyword(){
        cshrSearchResultsSteps.clearExistingKeyword();
    }

    @When("^I should see search filters displayed in search results page$")
    public void I_should_see_search_filters_displayed_in_search_results_page(){
        cshrSearchResultsSteps.verifySearchFiltersIsDisplayed();
    }

    @And("^I enter \"([^\"]*)\" in the keyword and refine$")
    public void  I_enter_in_the_keyword_and_refine(String newKeyword){
        cshrSearchResultsSteps.enterKeyword(newKeyword);
        cshrSearchResultsSteps.refineResults();
    }

    @When("^the results are more than \"([^\"]*)\"$")
    public void the_results_are_more_than_20(int totalResults){
         totalJobsFound = Integer.parseInt(cshrSearchResultsSteps.noOfJobsFound());
         this.totalResultsDefined=totalResults;
    }

    @Then("^a link to next page is displayed$")
    public void Then_a_link_to_next_page_is_displayed(){
        if(totalJobsFound > totalResultsDefined){
            Assert.assertTrue(cshrSearchResultsSteps.verifyNextIsDisplayed());
        }
    }

    @Then("^I click on the link to the last page$")
    public void I_click_on_the_link_to_the_last_page(){
        if(totalJobsFound > totalResultsDefined) {
            cshrSearchResultsSteps.navigateToLastPage();
        }
    }

    @Then("^a previous link is displayed$")
    public void a_previous_link_is_displayed(){
        if(totalJobsFound > totalResultsDefined){
           Assert.assertTrue(cshrSearchResultsSteps.verifyPrevIsDisplayed());
        }
    }

    @Then("^I should see only \"([^\"]*)\" pages listed$")
    public void I_should_see_only_five_pages_listed(int pages){
        Assert.assertEquals(pages,cshrSearchResultsSteps.noOfPageNumbersDisplayed());
    }

    @When("^the results are less than \"([^\"]*)\"$")
    public void  When_the_results_are_less_than(int jobsResultsNum){
        totalJobsFound = Integer.parseInt(cshrSearchResultsSteps.noOfJobsFound());
        this.totalResultsDefined = jobsResultsNum;
    }

    @When("^a link to next page is not displayed$")
    public void a_link_to_next_page_is_not_displayed(){
        if(totalJobsFound<=totalResultsDefined){
            Assert.assertFalse(cshrSearchResultsSteps.verifyNextIsDisplayed());
        }
    }
}
