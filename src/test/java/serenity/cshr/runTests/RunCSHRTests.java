package serenity.cshr.runTests;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"serenity.cshr.stepDefinitions"})
public class RunCSHRTests {

    //TODO
    //Connect to database and fetch results
    //Implement tests for working hours and contact details
    //The pages are not scrolling to the bottom only partial screenshots are obtained- should they be unzoomed

}
