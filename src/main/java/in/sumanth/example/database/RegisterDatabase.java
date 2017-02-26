package in.sumanth.example.database;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
public class RegisterDatabase {
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
    public String createuser(String user) throws JSONException,ClassNotFoundException {
        boolean empty = false;
        PreparedStatement stmtupdate = null;
        JSONObject obj = new JSONObject(user);
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM login_details where username =?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getString("username"));
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            if(!set.first()){
                empty = true;
            }
        }catch (SQLException e) {
            System.out.println("entered");
            empty = true;
        }finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            if (empty) {
                String query = "insert INTO login_details (username,password,firstname,lastname,usertype,email) VALUES (?,?,?,?,?,?);";
                try {
                    stmtupdate = conn.prepareStatement(query);
                    stmtupdate.setString(1, obj.getString("username"));
                    stmtupdate.setString(2, obj.getString("password"));
                    stmtupdate.setString(3, obj.getString("firstName"));
                    stmtupdate.setString(4, obj.getString("lastName"));
                    stmtupdate.setString(5, "student");
                    stmtupdate.setString(6, obj.getString("email"));
                    System.out.println(stmtupdate);
                    stmtupdate.executeUpdate();
                    result.put("success", true);
                    result.put("message", "User created successfully");
                }catch (SQLException e){
                    result.put("success", false);
                    result.put("message", "Failed to update details");
                    System.out.println(e);
                }finally {
                    try {
                        stmtupdate.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                result.put("success", false);
                result.put("message", "UserName already exists");
            }
        return result.toString();
    }
}
