package in.sumanth.example.database;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
public class LoginDatabase {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/login";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sumanth448";
    Connection conn = null;
    PreparedStatement stmt = null;
    //JSONArray array = new JSONArray();
    JSONObject result = new JSONObject();
    public String getpassword(String username) throws ClassNotFoundException {
        String a =null;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT password,firstname,usertype,email FROM login_details where username =?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            if(!set.first()){
                return a;
            }
            try {
                result.put("password",set.getString("password"));
                result.put("firstname",set.getString("firstname"));
                result.put("usertype",set.getString("usertype"));
                result.put("email",set.getString("email"));
                return result.toString();
            } catch (JSONException e) {
                return a;
            }

        }catch (SQLException e) {
            System.out.println("entered");
            return a;
        }finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
