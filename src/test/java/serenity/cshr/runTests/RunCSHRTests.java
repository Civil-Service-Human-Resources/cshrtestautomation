package serenity.cshr.runTests;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = {"src/test/resources/features/frontend"},
        glue = {"serenity.cshr.stepDefinitions"})
public class RunCSHRTests {
    }
