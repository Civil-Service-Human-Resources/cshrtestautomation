package rest.cshr;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class PostJobData extends ApiSetup {


    @Title("Post a valid job data with all fields matching requirement")
    @Test
    public void postJobData() throws Exception {
        Response statuscode = SerenityRest.rest().given().contentType("application/json").body(excelDataAsJson().get(0)).when().post("/vacancy");
        Assert.assertTrue(statuscode.body().asString().contains("probationary period are eligible to apply for"));
        JsonPath evaluatorForJsonPath = statuscode.jsonPath();
        String location = evaluatorForJsonPath.get("location");
        Assert.assertEquals(location, excelDataAsJson().get(1).get("location").getAsString());
    }

    @Title("Post an invalid job  with a missing mandatory field-location")
    @Test
    public void postInvalidJobData() throws Exception {
        Response response = SerenityRest.rest().given().contentType("application/json").body(excelDataAsJson().get(1).remove("location")).when().post("/vacancy");
        Assert.assertEquals(400, response.getStatusCode());

    }

   /* @Title("Post an invalid job with an extra field which doesn't exist in the schema and look for the error message returned")
    @Test
    public void postInvalidJobDataWithExtraField() throws Exception{
        JsonObject job = excelDataAsJson().get(1);
        job.addProperty("someKey","someVlaue");
        Response response = SerenityRest.rest().given().contentType("application/json").body(job).when().post("/vacancy");
        Assert.assertEquals(200,response.getStatusCode());
    }
*/
}
