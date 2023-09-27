package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.minidev.json.JSONObject;

/**
 * The DatabaseManager class provides methods to connect to a database, add new jobs to the database,
 * and retrieve the minimum and maximum levels for a given job.
 */
public class DatabaseManager {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/karate_db", "karate_qa", "karate_qa_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function returns a connection object.
     * 
     * @return The method is returning a Connection object.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * The addNewJob function inserts a new job into a database table with the specified job name,
     * minimum level, and maximum level.
     * 
     * @param jobName The jobName parameter is a String that represents the name or description of the
     * job that you want to add to the database.
     * @param min_lvl The min_lvl parameter represents the minimum level required for the job. It is an
     * integer value that specifies the minimum level of experience or skill needed for the job.
     * @param max_lvl The parameter "max_lvl" represents the maximum level required for the job.
     */
    public static void addNewJob(String jobName, int min_lvl, int max_lvl) {
        try {
            String sql = "INSERT INTO jobs (job_desc, min_lvl, max_lvl) VALUES ('"+jobName+"', '"+min_lvl+"', '"+max_lvl+"')";
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function retrieves the minimum and maximum levels for a given job name from a database and
     * returns them as a JSON object.
     * 
     * @param jobName The jobName parameter is a String that represents the name of the job for which
     * you want to retrieve the minimum and maximum levels.
     * @return The method is returning a JSONObject containing the minimum and maximum levels for a
     * job.
     */
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
