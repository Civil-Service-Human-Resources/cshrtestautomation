package rest.cshr;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;
import java.util.Map;

@RunWith(SerenityRunner.class)
public class PostJobData extends ApiSetup {


    @Title("Post a valid job data with all fields matching requirement")
    @Test
    public void postJobData() throws Exception {
        int excelRows = excelDataAsJson().size();
        for(int i=0;i<excelRows;i++){
            Response statuscode = SerenityRest.rest().given().contentType("application/json").body(excelDataAsJson().get(i)).when().post("/vacancy");
            statuscode.getBody().prettyPrint();
            Assert.assertTrue(statuscode.body().asString().contains("probationary period are eligible to apply for"));
            JsonPath evaluatorForJsonPath = statuscode.jsonPath();
            String location = evaluatorForJsonPath.get("location");
            Assert.assertEquals(location, excelDataAsJson().get(i).get("location").getAsString());
        }
    }

    @Title("Post an invalid job  with a missing mandatory field-location")
    @Test
    public void postInvalidJobData() throws Exception {
        Response response = SerenityRest.rest().given().contentType("application/json").body(excelDataAsJson().get(1).remove("location")).when().post("/vacancy");
        Assert.assertEquals(400, response.getStatusCode());

    }

    @Title("Get valid data using location only")
    @Test
    public void getUsingLocation(){
        /*LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);*/
        Response response = SerenityRest.rest().given().contentType("application/json").get("http://localhost:8080/vacancy/search/location/london");
        System.out.println(response.asString());
        int sizeOfRes = response.getBody().jsonPath().get("content.size()");
        List<Map<String,String>> jk = response.getBody().jsonPath().get("content");
        Assert.assertEquals("london".toLowerCase(), jk.get(1).get("location").toLowerCase());
    }
}
