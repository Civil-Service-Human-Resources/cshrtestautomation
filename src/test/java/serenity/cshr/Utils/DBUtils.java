package serenity.cshr.Utils;

import java.sql.*;
import java.util.Properties;

public class DBUtils {

    private static Connection conn = null;

    private String sqlForKeywordSearch = "SELECT * FROM vacancies WHERE CONCAT(title, ' ', description,' ',eligibility) ILIKE ? and responsibilities is not null;";
    private String sqlForKeywordSearchCount = "SELECT count(*) FROM vacancies WHERE CONCAT(title, ' ', description,' ',eligibility) ILIKE ? and responsibilities is not null;";
    private String sqlForLocationOnly = "SELECT * FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
    private String sqlForLocationOnlyCount = "SELECT count(*) FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
    private String sqlForKeywordAndLocationSearch = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";

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

    public  String searchByKeyword(String keyword) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForKeywordSearch);
        preStatement.setString(1, "%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
        +" "+rs.getString("role"));

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

        return result;
    }

    public  int countSearchByLocation(String location) throws SQLException {
        connectToDataBase();
        PreparedStatement preStatement = conn.prepareStatement(sqlForLocationOnlyCount);
        preStatement.setString(1, "%"+location+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        return count;
    }

    public  String searchByKeywordAndLocation(String keyword, String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sqlForKeywordAndLocationSearch);
        preStatement.setString(1,"%"+location+"%");
        preStatement.setString(2,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
                +" "+rs.getString("role"));

        return result;
    }

    public  int countSearchByKeywordAndLocation(String keyword, String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1,"%"+location+"%");
        preStatement.setString(2,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        preStatement.close();
        return count;
    }

}
