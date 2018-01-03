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
}
