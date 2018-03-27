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
    boolean clickedOnNextPage;

    @Steps
    CshrSearchResultsSteps cshrSearchResultsSteps;

    @And("^partial job description$")

    public void partial_job_description() {
        cshrSearchResultsSteps.verifyJobDescriptionExists();
    }

    @Then("^I should see all the results matching \"([^\"]*)\" in a new page$")
    public void i_should_see_all_the_results_matching_in_a_new_page(String arg1) {

    }

    @Then("^I should see only the results matching \"([^\"]*)\" in \"([^\"]*)\" and \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" in a new page$")
    public void i_should_see_only_the_results_matching_in_in_a_new_page(String keyword, String location, int radius, Double latitude, Double longitude ) {
        //Look for location in the html to see if all the locations are filtered according to the search radius string and compare it against the query
        cshrSearchResultsSteps.areTheResultsSameAsSearch(keyword,location,radius,latitude,longitude);
    }

    @Then("^I should see only the results matching \"([^\"]*)\" in \"([^\"]*)\" and \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\" or \"([^\"]*)\" or overseas locations in a new page$")
    public void  I_should_see_only_the_results_matching_keyword_location_radius_latitude_longitude_regions_or_overseas_locations_in_a_new_page(
            String keyword, String location, int radius, Double latitude, Double longitude, String region){
        cshrSearchResultsSteps.areTheResultsSameAsSearch(keyword,location,radius,latitude,longitude);
    }

    @Then("^I should see only the results matching \"([^\"]*)\" and \"([^\"]*)\" in \"([^\"]*)\" and \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void I_should_see_only_the_results_matching_the_department_matching(String keyword, String department,String location, int radius, Double latitude, Double longitude){
        cshrSearchResultsSteps.aretheCorrectJobsWithDeptsDisplayed( keyword,department,location,radius,latitude,longitude);
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

    @And("^I enter \"([^\"]*)\" in the keyword$")
    public void  I_enter_in_the_keyword_and_refine(String newKeyword){
        cshrSearchResultsSteps.enterKeyword(newKeyword);
    }

    @And("I click update results")
    public void I_click_refine(){
        cshrSearchResultsSteps.refineResults();
    }

    @When("^the results are more than \"([^\"]*)\"$")
    public void the_results_are_more_than(String totalResults){
        totalJobsFound = Integer.parseInt(cshrSearchResultsSteps.noOfJobsFound());
        String strtoint = totalResults.substring(0,2);
        if(strtoint.contains(" ")){
            this.totalResultsDefined=Integer.parseInt(strtoint.substring(0,1));
        }
        else{
            this.totalResultsDefined=Integer.parseInt(strtoint);
        }

    }

    @Then("^a link to next page is displayed$")
    public void Then_a_link_to_next_page_is_displayed(){
        if(totalJobsFound > totalResultsDefined){
            Assert.assertTrue(cshrSearchResultsSteps.verifyNextIsDisplayed());
        }
        else{
            Assert.assertFalse(cshrSearchResultsSteps.verifyNextIsDisplayed());
        }
    }

    @When("^I click on next page$" )
    public void I_click_on_next_page(){
        if(totalJobsFound>totalResultsDefined) {
            cshrSearchResultsSteps.navigateToNext();
            clickedOnNextPage = true;
        }
    }

    @Then("^a link to previous page is displayed$")
    public void a_link_to_previous_page_is_displayed(){
        if(clickedOnNextPage == true){
            cshrSearchResultsSteps.verifyPrevIsDisplayed();
        }
    }

    @Then("^a display results per page drop down is displayed with \"([^\"]*)\" and in the list \"([^\"]*)\"$")
    public void a_display_results_per_page_drop_down_is_displayed_with(String defaultNum, String optionsList){
        cshrSearchResultsSteps.isDropdownDefault(defaultNum);
        cshrSearchResultsSteps.listofOptionsInDropDown(optionsList);
    }

    @Then("^A display results per page drop down is displayed \"([^\"]*)\"$")
    public void a_display_results_per_page_drop_down_is_displayed(Boolean bool){
         cshrSearchResultsSteps.isDropdownPresent(bool);
    }

    @And("^The user changes the number of results to display to \"([^\"]*)\"$")
    public void The_user_changes_the_number_of_results_to_display_to(String resultsPerPage){
        cshrSearchResultsSteps.selectNoOfResultsToDisplay(resultsPerPage);
    }

    @And("^I scroll to the bottom of the page$")
    public void I_scroll_to_the_bottom_of_the_page(){
        cshrSearchResultsSteps.scrollToBottom();
    }

    @When("^I select \"([^\"]*)\" from the sidebar$")
    public void i_select_department_from_sidebar(String departments){
        cshrSearchResultsSteps.selectDepartments(departments);
    }

    @Then("^I verify either a logo or department name is displayed in the job description$")
    public void I_verify_a_logo_is_displayed_in_the_job_description(){
        cshrSearchResultsSteps.isAlogoDisplayed();
    }

    @And("^I enter new radius \"([^\"]*)\"$")
    public void I_enter_new_radius(String radius){
        cshrSearchResultsSteps.selectRadius(radius);
    }

    @And("^I expand departments accordion$")
    public void I_expand_departments_accordion(){
        cshrSearchResultsSteps.expandDeptsAcccordion();
    }

    //TODO
    @When("^The job is with \"([^\"]*)\" in \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and public opening date is \"([^\"]*)\"" +
            " and government opening date is \"([^\"]*)\" and internal opening date is \"([^\"]*)\" The job displayed is \"([^\"]*)\"$")
     public void The_job_public_opening_date_is_and_government_opening_date_is_and_internal_opening_date_is(String keyword, String location, Double latitude, Double longitude, int radius,
                                                                            String publicOpeningDate,String govOpeningDate,String internalOpeningDate, Boolean isDisplayed){
            //Count the number of jobs from the database to verify from the front end based on the dates
            //Select a job from the front end get the job reference id and check the database what dates are in the database and assert
           //This needs a proper test data
            cshrSearchResultsSteps.areTheResultsSameAsSearch(keyword,location,radius,latitude,longitude);
           //What to verify, backend already says only jobs that have public opening date > now are displayed Is this for the future when internal jobs are provided
            cshrSearchResultsSteps.queryBasedOnDates(publicOpeningDate,govOpeningDate,internalOpeningDate,isDisplayed);

    }

    @When("^I filter jobs based on salary bands \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_filter_jobs_based_on_salary_bands_min_max(String minSal, String maxSal){
        cshrSearchResultsSteps.selectMinSalary(minSal);
        cshrSearchResultsSteps.selectMaxSalary(maxSal);
    }

    @When("^Salary values in \"([^\"]*)\" should start from next range$")
    public void Salary_values_in_max_should_start_from_next_range(int maxSal){

    }

    @Then("^The salary \"([^\"]*)\" starts at \"([^\"]*)\"$")
    public void The_salary_starts_at(String salType, String nextVal){
        Assert.assertEquals(cshrSearchResultsSteps.smartSalary(salType),nextVal);
    }
}
