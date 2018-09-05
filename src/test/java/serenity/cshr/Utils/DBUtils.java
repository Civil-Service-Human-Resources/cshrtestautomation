package serenity.cshr.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DBUtils {

    private static Connection conn = null;

    private String selectDepartmentId = "select id from departments where name ilike ?; ";
   /* private String sqlForLocationSearchWithLongLatAndRadiusCount = "select count(distinct v.id) FROM vacancies v join vacancylocations vl on v.id=vl.vacancyid " +
            "WHERE v.public_opening_date IS NOT NULL AND v.public_opening_date <= now() " +
            "And closing_date >= now() and CONCAT(title, ' ', description,' ',eligibility) ILIKE ? AND ((point(?, ?) <@> point(longitude, latitude)) <= ? " +
            " or v.overseasjob = ? or v.regions ilike (?))";*/

   private String sqlForLocationSearchWithLongLatAndRadiusCount = "select count(distinct v.id) FROM vacancies v join vacancylocations vl on v.id=vl.vacancyid " +
            "where point(?,?) <@> point(vl.longitude, vl.latitude) <= ?" +
           "and v.overseasjob=? and CONCAT(title, ' ', description,' ',eligibility) ILIKE ?" +
           "and v.public_opening_date IS NOT NULL AND v.public_opening_date <= now() And closing_date >= now() or v.regions ilike (?)" +
           ";";
    /*private String sqlForLocationDeptFilterWithLongitudeAndLatitude = "select count(distinct v.id) FROM vacancies v join vacancylocations vl on v.id=vl.vacancyid " +
            "WHERE v.public_opening_date IS NOT NULL AND v.public_opening_date <= now() " +
            "And closing_date >= now() and CONCAT(title, ' ', description,' ',eligibility) ILIKE ? AND ((point(?, ?) <@> point(longitude, latitude)) <= ? " +
            " or v.overseasjob = ? or v.regions ilike (?)) And dept_id in";
 */   private String sqlForLocationDeptFilterWithLongitudeAndLatitude = "select count(distinct v.id) FROM vacancies v join vacancylocations vl on v.id=vl.vacancyid " +
            "where point(?, ?) <@> point(vl.longitude, vl.latitude) <= ?" +
            "and v.overseasjob=? and CONCAT(title, ' ', description,' ',eligibility) ILIKE ?" +
            "and v.dept_id in(90,38) and v.public_opening_date IS NOT NULL AND v.public_opening_date <= now() And closing_date >= now() or v.regions ilike ('%South West%');";

    private String sqlForSelectingBasedOnGovIntPublicOpeningDates1 = " and v.government_opening_date ";
    private String sqlForSelectingBasedOnGovIntPublicOpeningDates2=       " and v.internal_opening_date " ;
    private String sqlForSelectingBasedOnGovIntPublicOpeningDates3=        " and v.public_opening_date ";

    public int  countForSelectingBasedOnOpeningDates(String keyword,String location,int radius, Double latitude,Double longitude,
                                                     String regions, Boolean overseas,String publicOpeningDate,String govOpeningDate,String internalOpeningDate ) throws SQLException{
        String pastOrFururePub =null;
        String pastOrFutureGov =null;
        String pastOrFutureInt =null;
        switch(publicOpeningDate){
            case "past"  : pastOrFururePub = "<now()";
            break;
            case "future":pastOrFururePub = ">=now()";
            break;
        }
        switch(govOpeningDate){
            case "past"  : pastOrFutureGov = "<now()";
            break;
            case "future":pastOrFutureGov = ">=now()";
            break;
        }
        switch(internalOpeningDate){
            case "past"  : pastOrFutureInt = "<now()";
            break;
            case "future":pastOrFutureInt = ">=now()";
            break;
        }
        connectToDataBase();
        StringBuilder query = new StringBuilder();
        query.append(sqlForSelectingBasedOnGovIntPublicOpeningDates1).append(pastOrFutureGov)
                .append(sqlForSelectingBasedOnGovIntPublicOpeningDates2).append(pastOrFutureInt)
                .append(sqlForSelectingBasedOnGovIntPublicOpeningDates3).append(pastOrFururePub);
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationSearchWithLongLatAndRadiusCount+query.toString());
        preStatement.setDouble(1,longitude);
        preStatement.setDouble(2,latitude);
        preStatement.setInt(3,radius);
        preStatement.setString(5,"%"+keyword+"%");
        preStatement.setBoolean(4,overseas);
        preStatement.setString(6,"%"+regions+"%");
        System.out.println("The preparedstmnt is: "+ preStatement);
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preStatement.close();
        conn.close();
        return count;
    }

    public static Connection connectToDataBase() throws SQLException {
        try {
            String url = "jdbc:postgresql://localhost/cshr";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "qwerty");
            conn = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public  int countSearchByKeywordAndLocation(String keyword, String location, int radius,Double latitude, Double longitude,String regions,Boolean overseas) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationSearchWithLongLatAndRadiusCount);
        preStatement.setDouble(1,longitude);
        preStatement.setDouble(2,latitude);
        preStatement.setInt(3,radius);
        preStatement.setString(5,"%"+keyword+"%");
        preStatement.setBoolean(4,overseas);
        preStatement.setString(6,"%"+regions+"%");
        System.out.println("The verify sql stmnt is: "+preStatement);
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preStatement.close();
        conn.close();
        return count;
    }

    public int countSearchByKeywordDeptAndLocation(String keyword, String location, int radius, Double latitude, Double longitude,String regions,Boolean overseas,String department) throws SQLException{
        connectToDataBase();

        ArrayList<Integer> items= this.getDepartmentId(department);
        StringBuilder query = new StringBuilder();
        query.append(sqlForLocationDeptFilterWithLongitudeAndLatitude).append("(");
        for(int i=0;i<items.size();i++){
            query.append(items.get(i)).append(",");
            if(i==items.size()-1){
                query.deleteCharAt(query.length()-1);
                query.append(")");
            }
        }
        PreparedStatement preparedStatement = conn.prepareStatement(query.toString());
        preparedStatement.setDouble(2,longitude);
        preparedStatement.setDouble(3,latitude);
        preparedStatement.setInt(4,radius);
        preparedStatement.setString(1,"%"+keyword+"%");
        preparedStatement.setBoolean(5,overseas);
        preparedStatement.setString(6,"%"+regions+"%");
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preparedStatement.close();
        conn.close();
        return count;
    }

    private ArrayList<Integer> getDepartmentId(String department) throws SQLException{

        List<String> items= new ArrayList<>();

        if(department.contains(",")){
            items = Arrays.asList(department.split(","));
        }else{
            items = Arrays.asList(department);
        }

        ArrayList<Integer> convertedDeptIds = new ArrayList<>();

        for (int i=0;i<items.size();i++){
            connectToDataBase();
            PreparedStatement preparedStatement = conn.prepareStatement(selectDepartmentId);
            preparedStatement.setString(1,items.get(i));
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            convertedDeptIds.add(rs.getInt("id"));
            preparedStatement.close();
        }
        return convertedDeptIds;
    }

}
