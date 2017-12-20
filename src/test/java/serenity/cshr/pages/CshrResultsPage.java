package serenity.cshr.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CshrResultsPage extends PageObject {

    public CshrResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "job-search__item-attributes-label")
    private WebElement jobDescription;

    @FindBy(className = "job-search__total-results")
    private WebElement totalJobs;


    public boolean jobDescriptionExists() {
        return jobDescription.isDisplayed();
    }

/*
  @WhenPageOpens
  public void waitForPage() {
    // wait for google sub menu: Web | Images | Videos | ...
    $("#hdtb-msb").waitUntilVisible();
  }

  public List<String> getResultsList() {
    List<String> resultList = new ArrayList<>();
    for (WebElement element : linkTitlesList) {
      resultList.add(element.getText());
    }
    return resultList;
  }*/


}
