package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement refine;

    @FindBy(linkText="Next")
    private WebElement nextLink;

    @FindBy(linkText="Prev")
    private WebElement prevLink;

    @FindBy(className="pagination__summary")
    private WebElement paginationSummary;

    @FindBy(id = "keyword")
    private WebElement keyword;

    @FindBy(className =  "search-filters")
    private WebElement searchFilters;

    @FindBy(css = "[aria-label^=\"Go to page\"]")
    private List<WebElement> currentPageList;

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

    public boolean isPaginationSummaryDisplayed(){
        return paginationSummary.isDisplayed() && paginationSummary.getText().contains("Showing");
    }

    public void clickPrevious(){

        prevLink.click();
    }

    public void clickNext(){
        nextLink.click();
    }

    public void clickLastPageLink(){
        element(currentPageList.get(noOfPageLinks()-1)).click();
    }

    public int noOfPageLinks(){
        return currentPageList.size();
    }
    public void clickRefine(){
        refine.click();
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
    public boolean isPrevDisplayed(){
        return element(prevLink).isCurrentlyEnabled();
    }

}
