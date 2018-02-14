package serenity.cshr.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DBUtils {

    private static Connection conn = null;

    private String sqlForKeywordSearch = "SELECT * FROM vacancies WHERE CONCAT(title, ' ', description,' ',eligibility) ILIKE ? and responsibilities is not null;";
    private String sqlForKeywordSearchCount = "SELECT count(*) FROM vacancies WHERE CONCAT(title, ' ', description,' ',eligibility) ILIKE ? and responsibilities is not null;";
    private String sqlForLocationOnly = "SELECT * FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
    private String sqlForLocationOnlyCount = "SELECT count(*) FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
    private String sqlForKeywordAndLocationSearch = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
    private String selectDepartmentId = "select id from departments where name ilike ?; ";
    private String sqlForLocationSearchWithLongLatAndRadiusCount = "select count(*) FROM vacancies WHERE public_opening_date IS NOT NULL AND public_opening_date <= now() " +
                                                                    "AND (point(?, ?) <@> point(longitude, latitude)) <= ? and CONCAT(title, ' ', description,' ',eligibility) ILIKE ?" +
                                                                    ";";
    private String sqlForLocationDeptFilterWithLongitudeAndLatitude = "select count(*) FROM vacancies WHERE public_opening_date IS NOT NULL AND public_opening_date <= now() " +
                                                                    "AND (point(?, ?) <@> point(longitude, latitude)) <= ? and CONCAT(title, ' ', description,' ',eligibility) ILIKE ?" +
                                                                    "And dept_id in";

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
/*
    public  String searchByKeyword(String keyword) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForKeywordSearch);
        preStatement.setString(1, "%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
        +" "+rs.getString("role"));
        preStatement.close();
        return result;
    }

    public  int countSearchByKeyword(String keyword) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForKeywordSearchCount);
        preStatement.setString(1, "%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        int count = rs.getInt(1);
        return count;
    }

    public  String searchByLocation(String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationOnly);
        preStatement.setString(1, "%"+location+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
                +" "+rs.getString("role"));
        preStatement.close();
        return result;
    }

    public  int countSearchByLocation(String location) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationOnlyCount);
        preStatement.setString(1, "%"+location+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        int count = rs.getInt(1);
        preStatement.close();
        return count;
    }

    public  String searchByKeywordAndLocation(String keyword, String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ?;";
        PreparedStatement preStatement = conn.prepareStatement(sqlForKeywordAndLocationSearch);
        preStatement.setString(1,"%"+location+"%");
        preStatement.setString(2,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
                +" "+rs.getString("role"));
        preStatement.close();
        return result;
    }*/

    public  int countSearchByKeywordAndLocation(String keyword, String location, int radius,Double latitude, Double longitude) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationSearchWithLongLatAndRadiusCount);
        preStatement.setDouble(1,longitude);
        preStatement.setDouble(2,latitude);
        preStatement.setInt(3,radius);
        preStatement.setString(4,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preStatement.close();
        return count;
    }

    public int countSearchByKeywordDeptAndLocation(String keyword, String department,String location, int radius, Double latitude, Double longitude) throws SQLException{
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
        preparedStatement.setDouble(1,longitude);
        preparedStatement.setDouble(2,latitude);
        preparedStatement.setInt(3,radius);
        preparedStatement.setString(4,"%"+keyword+"%");
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preparedStatement.close();
        System.out.println("count : "+ count);
        return count;
    }

    public ArrayList<Integer> getDepartmentId(String department) throws SQLException{

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
