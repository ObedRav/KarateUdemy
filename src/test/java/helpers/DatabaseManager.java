package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.minidev.json.JSONObject;

public class DatabaseManager {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/karate_db", "karate_qa", "karate_qa_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void addNewJob(String jobName, int min_lvl, int max_lvl) {
        try {
            String sql = "INSERT INTO jobs (job_desc, min_lvl, max_lvl) VALUES ('"+jobName+"', '"+min_lvl+"', '"+max_lvl+"')";
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getMinAndMaxLevelsForJob(String jobName) {
        try {
            JSONObject json = new JSONObject();
            String sql = "SELECT min_lvl, max_lvl FROM jobs WHERE job_desc = '"+jobName+"'";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            rs.next();

            json.put("min_lvl", rs.getString("min_lvl"));
            json.put("max_lvl", rs.getString("max_lvl"));

            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
