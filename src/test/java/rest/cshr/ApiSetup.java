package rest.cshr;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
            String subHead = null;
            JsonObject dept= new JsonObject() ;
            for (int i = 0; i < lastCell; i++) {
                if(sheetOne.getRow(0).getCell(i).toString().contains("/")){ //Splits heading and converts to
                    List<String> headSplit = Arrays.asList(sheetOne.getRow(0).getCell(i).toString().split("/"));
                    if(headSplit.get(1).equals("id")){
                        dept.addProperty(headSplit.get(1), sheetOne.getRow(rows + 1).getCell(i).getNumericCellValue());
                    }
                    else {
                        dept.addProperty(headSplit.get(1), sheetOne.getRow(rows + 1).getCell(i).toString());
                    }
                    if(subHead!=null){

                    }
                    else{
                        subHead = headSplit.get(0);
                    }
                }
                else{
                    if(sheetOne.getRow(0).getCell(i).toString().contains("sal")||sheetOne.getRow(0).getCell(i).toString().contains("acancies")){
                        dataConvert.addProperty(sheetOne.getRow(0).getCell(i).toString(), (int)sheetOne.getRow(rows + 1).getCell(i).getNumericCellValue());
                    }else {
                        dataConvert.addProperty(sheetOne.getRow(0).getCell(i).toString(), sheetOne.getRow(rows + 1).getCell(i).toString());
                    }
                }

            }
            dataConvert.add(subHead,dept);
            excelRows.add(dataConvert);
        }
        fis.close();
        return excelRows;
    }

    public static void main(String[] args) throws Exception{
        ApiSetup as = new ApiSetup();
        System.out.println(as.excelDataAsJson());
    }
}
