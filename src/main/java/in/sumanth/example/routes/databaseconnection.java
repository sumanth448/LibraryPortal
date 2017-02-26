package in.sumanth.example.routes;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by sumanth.reddy on 02/12/16.
 */
public class databaseconnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/library";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sumanth448";
    Connection conn = null;
    PreparedStatement stmt = null;
    //JSONArray array = new JSONArray();
    JSONObject obj = new JSONObject();
    public String getdetailsbyID(String ID) throws SQLException, ClassNotFoundException, JSONException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String sql = "SELECT * FROM cats where name = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1,ID);
        ResultSet set = stmt.executeQuery();
        while(set.next()){
            obj.put("owner",set.getString("owner"));
            obj.put("dob",set.getString("birth"));
            //array.put(obj);
        }
        return obj.toString();
    }
}

