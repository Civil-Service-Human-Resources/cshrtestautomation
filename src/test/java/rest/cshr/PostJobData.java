package rest.cshr;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PostJobData{

    String title = "Architect";
    String description = "Technical Architect - Birmingham - Circa £65K C#, .NET. Our growing client based near Manchester is currently looking for a skilled Technical Architect. " +
            "The role will be to provide leadership in technologies, system design";
    String location = "Birmingham";
    int salMin = 30000;
    int salMax = 40000;
    int noOfVacancies = 2;
    String closingDate = "10 April 2018";
    String grade = "band 2";
    String role = "Bring to the table win-win survival strategies to ensure proactive domination. " +
            "At the end of the day, going forward, a new normal that has evolved from generation X is on the runway heading towards a streamlined cloud solution.";
    String responsibilities = "Capitalize on low hanging fruit to identify a ballpark value added activity to beta test.";
    String workingHours = "37.5 per week";
    String contactName = "Joe Bloggs";
    String contactDept = "HMRC";
    String contactEmail = "hmrc@hmrc.com";
    String contactTelephone = "01234 56789000";
    String eligibility = "Candidates in their probationary period are eligible to apply for vacancies within this department.";




       @Title("Post a valid job data")
        @Test
        public void postJobData(){
           JsonObject jobDetails = new JsonObject();

           jobDetails.addProperty("title",title);
           jobDetails.addProperty("description",description);
           jobDetails.addProperty("location",location);
           jobDetails.addProperty("salaryMin",salMin);
           jobDetails.addProperty("salaryMax",salMax);
           jobDetails.addProperty("numberVacancies",noOfVacancies);
           jobDetails.addProperty("numberVacancies",noOfVacancies);
           jobDetails.addProperty("grade",grade);
           jobDetails.addProperty("role", role);
           jobDetails.addProperty("responsibilities",responsibilities);
           jobDetails.addProperty("workingHours",workingHours);
           jobDetails.addProperty("contactName",contactName);
           jobDetails.addProperty("contactDepartment",contactDept);
           jobDetails.addProperty("contactEmail",contactEmail);
           jobDetails.addProperty("contactTelephone",contactTelephone);
           jobDetails.addProperty("eligibility",eligibility);
           jobDetails.addProperty(	"closingDate",closingDate);

           Response statuscode = SerenityRest.rest().given().contentType("application/json").body(jobDetails).when().post("http://localhost:8080/vacancy");
           Assert.assertTrue(statuscode.body().asString().contains("probationary period are eligible to apply for"));
           JsonPath evaluatorForJsonPath = statuscode.jsonPath();
           String location = evaluatorForJsonPath.get("location");
           Assert.assertEquals(location, this.location);
        }

    @Title("Post a invalid job data")
    @Test
    public void postInvalidJobData(){
        JsonObject jobDetails = new JsonObject();

        //jobDetails.addProperty("title",title);
        jobDetails.addProperty("description",description);
        jobDetails.addProperty("location",location);
        jobDetails.addProperty("salaryMin",salMin);
        jobDetails.addProperty("salaryMax",salMax);
        jobDetails.addProperty("numberVacancies",noOfVacancies);
        jobDetails.addProperty("numberVacancies",noOfVacancies);
        jobDetails.addProperty("grade",grade);
        jobDetails.addProperty("role", role);
        jobDetails.addProperty("responsibilities",responsibilities);
        jobDetails.addProperty("workingHours",workingHours);
        jobDetails.addProperty("contactName",contactName);
        jobDetails.addProperty("contactDepartment",contactDept);
        jobDetails.addProperty("contactEmail",contactEmail);
        jobDetails.addProperty("contactTelephone",contactTelephone);
        jobDetails.addProperty("eligibility",eligibility);
        jobDetails.addProperty(	"closingDate",closingDate);

        Response response = SerenityRest.rest().given().contentType("application/json").body(jobDetails).when().post("http://localhost:8080/vacancy");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);

    }
}
