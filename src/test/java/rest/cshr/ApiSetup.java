package rest.cshr;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ApiSetup {
    @Before
    public void setUp(){
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost:8080";
        }else {
            RestAssured.baseURI = baseHost;
        }
    }

    public List<JsonObject> excelDataAsJson() throws Exception {
        File source = new File(System.getProperty("user.dir") + "/src/test/resources/data/JobData.xlsx");
        FileInputStream fis = new FileInputStream(source);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheetOne = wb.getSheetAt(0);
        int lastCell = sheetOne.getRow(0).getLastCellNum();
        int noOfRows = sheetOne.getPhysicalNumberOfRows();
        List<JsonObject> excelRows = new ArrayList<>();
        for(int rows=0;rows<noOfRows-1;rows++){
            JsonObject dataConvert = new JsonObject();
            for (int i = 0; i < lastCell; i++) {
                dataConvert.addProperty(sheetOne.getRow(0).getCell(i).toString(), sheetOne.getRow(rows + 1).getCell(i).toString());
            }
            excelRows.add(dataConvert);
        }
        fis.close();
        return excelRows;
    }

}
