package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class CshrResultsPage extends PageObject {

    public CshrResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "job-search__item-attributes-value")
    private WebElement jobDescription;

    @FindBy(className = "job-search__item-attributes-label")
    private WebElement noOfVacancies;

    @FindBy(className = "job-search__item-title")
    private WebElement jobTitle;

    @FindBy(css = "a[href*='/job/details']")
    private WebElement jobTitleLink;

    @FindBy(linkText = "Back to search")
    private WebElement backToSearch;

    @FindBy(id = "search-results-total")
    private WebElement searchResultsTotal;

    @FindBy(linkText = "please try a new search")
    private WebElement tryNewSearchLink;

    @FindBy(xpath = "//li[contains(@id,'-numvacancies')]/span")
    private List<WebElement> vacanciesNumber;

    @FindBy(xpath = "//li[contains(@id,'-location')]/span")
    private List<WebElement> locationNumber;

    @FindBy(xpath = "//li[contains(@id,'-salary')]/span")
    private List<WebElement> salaryNumber;

    @FindBy(xpath = "//li[contains(@id,'-closingdate')]/span")
    private List<WebElement> closingDateNumber;

    @FindBy(xpath = "//li[contains(@id,'-grade')]/span")
    private List<WebElement> jobGradeNumber;

    @FindBy(className = "button")
    private WebElement updateResults;

    @FindBy(css="[aria-label=\"Go to next page\"]")
    private WebElement nextLink;

    @FindBy(css="[aria-label=\"Go to previous page\"]")
    private WebElement prevLink;

    @FindBy(id = "keyword")
    private WebElement keyword;

    @FindBy(className =  "search-filters")
    private WebElement searchFilters;
/*
    @FindBy(css = "[aria-label^=\"Go to page\"]")
    private List<WebElement> currentPageList;*/

    @FindBy(css =".form-multicheckbox__item")
    private List<WebElement> deptCheckboxes;

    @FindBy(id= "dept-1")
    private WebElement deptt;

    @FindBy(id = "rpp")
    private WebElement resultsPerPageDropDown;

    @FindBy(css ="[aria-controls=\"department-list\"]")
    private WebElement departmentAccordion;

    @FindBy(id="radius")
    private WebElement radiusDropDown;

    public String jobDescriptionExists() {
        return jobDescription.getText();
    }

    public String noOfVacanciesText() {
        return noOfVacancies.getText();
    }

    public CshrFullJobDescriptionPage clickOnJobTitle() {
        jobTitleLink.click();
        return new CshrFullJobDescriptionPage(getDriver());
    }

    public void clickBackToSearch() {
        backToSearch.click();
    }

    public String searchResultsTotalNum(){
        return searchResultsTotal.getText();
    }

    public void tryNewSeach(){
        tryNewSearchLink.click();
    }

    public int listOfVacanciesNumber(){
        int i=0;
        for (WebElement e:vacanciesNumber) {
            if(!e.getText().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public int listOfLocationNumber(){
        int i=0;
        for (WebElement e:locationNumber) {
            if(!e.getText().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public int listOfSalaryNumber(){
        int i=0;
        for (WebElement e:salaryNumber) {
            if(!e.getText().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public int listOfClosingDateNumber(){
        int i=0;
        for (WebElement e:closingDateNumber) {
            if(!e.getText().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public int listOfJobGradeNumber(){
        int i=0;
        for (WebElement e:jobGradeNumber) {
            if(!e.getText().isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public void scrolltoBottom(){
        this.scroll(resultsPerPageDropDown);
    }

    public void scroll(WebElement ele){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(ele).build().perform();
    }

    public void clickNext(){
        nextLink.click();
    }

   /* public int noOfPageLinks(){
        return currentPageList.size();
    }*/
    public void clickRefine(){
        updateResults.click();
    }

    public void clearKeyword(){
        element(keyword).clear();
    }

    public void isSearchFiltersDisplayed(){
        element(searchFilters).isDisplayed();
    }

    public void enterKeyword(String newKeyword){
        element(keyword).type(newKeyword);
    }

    public boolean isNextDisplayed(){
       return element(nextLink).isCurrentlyEnabled();
    }

    public boolean isprevDisplayed(){
        return element(prevLink).isCurrentlyEnabled();
    }
    public void selectDepartments(String departments){
        String [] depts = departments.split(",");

        List<String> deptCheckboxNameList = new ArrayList<>();
        for(WebElement e: deptCheckboxes){
            deptCheckboxNameList.add(e.getText().toLowerCase());
        }
        for(int i=0;i<depts.length;i++){
            if(deptCheckboxNameList.contains(depts[i].toLowerCase())){
                deptCheckboxes.get(deptCheckboxNameList.indexOf(depts[i].toLowerCase())).click();
            }
        }

    }

    public boolean isdisplayResultsDropdownPresent(){
        return resultsPerPageDropDown.isDisplayed();
    }

    public String getDefaultPerPage(){
        String select = new Select(resultsPerPageDropDown).getFirstSelectedOption().getText();
        return select;
    }

    public ArrayList<String> getListOfDropDown(){
        Select select = new Select(resultsPerPageDropDown);
        List<WebElement> dpOptions = select.getOptions();
        ArrayList<String> dpOptionsList = new ArrayList<>();
        for(WebElement e: dpOptions){
           dpOptionsList.add( e.getText());
        }
        return dpOptionsList;
    }

    public void selectResultsNumbertoDisplay(String resultsPerPage){
        resultsPerPageDropDown.sendKeys(resultsPerPage);
    }

    public void clickDeptAccordion(){
        departmentAccordion.click();
    }

    public void selectRadiusDropDown(String radius){
        radiusDropDown.sendKeys(radius);
    }
}
