package serenity.cshr.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import serenity.cshr.Utils.DBUtils;
import serenity.cshr.pages.CshrHomePage;
import serenity.cshr.pages.CshrResultsPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CshrSearchResultsSteps {
    private CshrResultsPage cshrResultsPage;
    private DBUtils dbUtils;
    private int keywordAndLocationCount;
    private int keywordDeptAndLocationCount;

    @Step
    public void verifyJobDescriptionExists() {
        Assert.assertTrue(!cshrResultsPage.jobDescriptionExists().isEmpty());
    }

    @Step
    public String noOfJobsFound() {
        return cshrResultsPage.searchResultsTotalNum();
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

    @Step
    public void clearExistingKeyword(){
        cshrResultsPage.clearKeyword();
    }
    @Step
    public void verifySearchFiltersIsDisplayed(){
        cshrResultsPage.isSearchFiltersDisplayed();
    }

    @Step
    public void enterKeyword(String newKeyword){
        cshrResultsPage.enterKeyword(newKeyword);
    }

    @Step
    public void refineResults(){
        cshrResultsPage.clickRefine();
    }
    @Step
    public void navigateToNext(){

        cshrResultsPage.clickNext();
    }

    @Step
    public void scrollToBottom(){
        cshrResultsPage.scrolltoBottom();
    }
    @Step
    public boolean verifyNextIsDisplayed(){

        return cshrResultsPage.isNextDisplayed();
    }

    public boolean verifyPrevIsDisplayed(){
        return cshrResultsPage.isprevDisplayed();
    }
    @Step
    public void areTheResultsSameAsSearch(String keyword,String location, int radius,Double latitude, Double longitude ){
        try {
            dbUtils = new DBUtils();
            keywordAndLocationCount= dbUtils.countSearchByKeywordAndLocation(keyword, location,radius, latitude,longitude);
            Assert.assertEquals(keywordAndLocationCount,Integer.parseInt(cshrResultsPage.searchResultsTotalNum()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Step
    public void aretheCorrectJobsWithDeptsDisplayed(String keyword, String department,String location, int radius, Double latitude, Double longitude ){
        try {
            dbUtils = new DBUtils();
            keywordDeptAndLocationCount= dbUtils.countSearchByKeywordDeptAndLocation(keyword, department, location,radius, latitude,longitude);
            Assert.assertEquals(keywordDeptAndLocationCount,Integer.parseInt(cshrResultsPage.searchResultsTotalNum()));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        //Verify that the department boxes are checked?
    }

    @Step
    public void selectRadius(String radius){
        cshrResultsPage.selectRadiusDropDown(radius);
    }
    @Step
    public void selectDepartments(String departments){
        cshrResultsPage.selectDepartments(departments);
    }
    @Step
    public void isDropdownDefault(String defaultNum){
        Assert.assertTrue(cshrResultsPage.isdisplayResultsDropdownPresent());
        Assert.assertEquals(defaultNum,cshrResultsPage.getDefaultPerPage());
    }

    @Step
    public void isDropdownPresent(boolean bool){
        Assert.assertEquals(bool,cshrResultsPage.isdisplayResultsDropdownPresent());
    }

    @Step
    public void listofOptionsInDropDown(String optionsList){
        List<String> dropDowns = Arrays.asList(optionsList.split(","));
        Assert.assertTrue(dropDowns.equals(cshrResultsPage.getListOfDropDown()));

    }

    @Step
    public void selectNoOfResultsToDisplay(String resultsPerPage){
        cshrResultsPage.selectResultsNumbertoDisplay(resultsPerPage);
    }

    @Step
    public void expandDeptsAcccordion(){
        cshrResultsPage.clickDeptAccordion();
    }
    //TODO
    @Step
    public void queryBasedOnDates(String publicOpeningDate,String govOpeningDate,String internalOpeningDate,Boolean isDisplayed){

    }

    @Step
    public void isAlogoDisplayed(){
        Assert.assertTrue(cshrResultsPage.isAlogoDisplayed());
    }

    @Step
    public void selectMinSalary(String minSal){
        cshrResultsPage.selectMinsSal( minSal);
    }

    @Step
    public void selectMaxSalary(String maxSal){
        cshrResultsPage.selectMaxSalary(maxSal);
    }

    @Step
    public String smartSalary(String maxOrMin){
       return cshrResultsPage.smartSalryVal(maxOrMin);
    }

}
