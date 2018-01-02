package serenity.cshr.Utils;

import java.sql.*;
import java.util.Properties;

public class DBUtils {

    private static Connection conn = null;

    public static Connection connectToDataBase() throws SQLException {
        try {
            String url = "jdbc:postgresql://localhost/cshr";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "qwerty");
            //props.setProperty("ssl", "false");
            conn = DriverManager.getConnection(url, props);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static String searchByKeyword(String keyword) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1, "%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
        +" "+rs.getString("role"));

        return result;
    }

    public static int countSearchByKeyword(String keyword) throws SQLException {
        connectToDataBase();
        String sql = "SELECT count(*) FROM vacancies WHERE CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1, "%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        int count = rs.getInt(1);
        return count;
    }

    public static String searchByLocation(String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1, "%"+location+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
                +" "+rs.getString("role"));

        return result;
    }

    public static int countSearchByLocation(String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT count(*) FROM vacancies WHERE location ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1, "%"+location+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();

        int count = rs.getInt(1);

        return count;
    }

    public static String searchByKeywordAndLocation(String keyword, String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1,"%"+location+"%");
        preStatement.setString(2,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        String result = (rs.getString("description")+ " "+rs.getString("title")+" "+rs.getString("eligibility")+" "+rs.getString("responsibilities")
                +" "+rs.getString("role"));

        return result;
    }

    public static int countSearchByKeywordAndLocation(String keyword, String location) throws SQLException {
        connectToDataBase();
        String sql = "SELECT * FROM vacancies WHERE location ILIKE ? AND CONCAT(title, ' ', description) ILIKE ? and responsibilities is not null;";
        PreparedStatement preStatement = conn.prepareStatement(sql);
        preStatement.setString(1,"%"+location+"%");
        preStatement.setString(2,"%"+keyword+"%");
        ResultSet rs = preStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }

}
