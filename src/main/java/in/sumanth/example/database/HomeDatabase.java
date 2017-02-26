package in.sumanth.example.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
public class HomeDatabase {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/library";
    static final String DB_URL_LOGIN = "jdbc:mysql://localhost/login";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sumanth448";
    Connection conn = null;
    PreparedStatement stmt = null;
    JSONArray array = new JSONArray();
    public String getbooks() throws ClassNotFoundException {
        String a =null;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM library_books;";
        try {
            stmt = conn.prepareStatement(sql);
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            System.out.println(set);
            if(!set.first()){
                return a;
            }
            int i=1;
            try {
                if(set.first()){
                    JSONObject result = new JSONObject();
                    result.put("id",set.getString("id"));
                    result.put("book",set.getString("bookname"));
                    result.put("date",set.getString("updated_at"));
                    result.put("status",set.getString("status"));
                    result.put("issuedfrom",set.getString("issuefrom"));
                    result.put("issuedto",set.getString("issuedto"));
                    array.put(result);
                    i++;
                }
                while(set.next())
                {
                    JSONObject result = new JSONObject();
                    result.put("id",set.getString("id"));
                    result.put("book",set.getString("bookname"));
                    result.put("date",set.getString("updated_at"));
                    result.put("status",set.getString("status"));
                    result.put("issuedfrom",set.getString("issuefrom"));
                    result.put("issuedto",set.getString("issuedto"));
                    array.put(result);
                    i++;
                }
                System.out.println(array);
                return array.toString();
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
    public String getemaildetails() throws ClassNotFoundException {
        System.out.println("eneted get email");
        String a =null;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(DB_URL_LOGIN,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT email FROM login_details where usertype='student'";
        try {
            stmt = conn.prepareStatement(sql);
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            System.out.println(set);
            if(!set.first()){
                return a;
            }
            int i=1;
            try {
                if(set.first()){
                    JSONObject result = new JSONObject();
                    result.put("index",i);
                    result.put("email",set.getString("email"));
                    array.put(result);
                    i++;
                }
                while(set.next())
                {
                    JSONObject result = new JSONObject();
                    result.put("index",i);
                    result.put("email",set.getString("email"));
                    array.put(result);
                    i++;
                }
                System.out.println(array);
                return array.toString();
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

    public String updatebookdetails(String bookdetails) throws ClassNotFoundException, JSONException {
        System.out.println("eneted get book");
        String a =null;
        boolean empty = true;
        Class.forName("com.mysql.jdbc.Driver");
        JSONObject result = new JSONObject();
        JSONObject update = new JSONObject(bookdetails);
        PreparedStatement stmtupdate = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM library_books where bookname =?;";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, update.getString("book"));
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            if(set.first() && set.getString("id").equals(update.getString("id"))){
                empty = true;
            }else{
                empty = false;
            }
        }catch (SQLException e) {
            System.out.println("entered");
            result.put("success", false);
            result.put("message", "Something went wrong...Please try again");
            return result.toString();
        }finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(empty) {
            try {
                try {
                    String sql = "UPDATE library_books SET bookname = ? , updated_at = ? , status = ? , issuefrom = ? , issuedto = ?" + "WHERE id = ?";
                    stmtupdate = conn.prepareStatement(sql);
                    stmtupdate.setString(1, update.getString("book"));
                    stmtupdate.setString(2, update.getString("date"));
                    stmtupdate.setString(3, update.getString("status"));
                    stmtupdate.setString(4, update.getString("issuedfrom"));
                    stmtupdate.setString(5, update.getString("issuedto"));
                    stmtupdate.setString(6, update.getString("id"));
                    System.out.println(stmtupdate);
                    int records = stmtupdate.executeUpdate();
                    System.out.println(result);
                    if (records == 0) {
                        result.put("success", false);
                        result.put("message", "Failed to update details");
                        return result.toString();
                    }
                    result.put("success", true);
                    result.put("message", "Book Details Updated successfully");
                    return result.toString();
                } catch (JSONException e) {
                    result.put("success", false);
                    result.put("message", "Failed to update details");
                    return result.toString();
                }
            } catch (SQLException e) {
                System.out.println("entered");
                result.put("success", false);
                result.put("message", "Failed to update details");
                return result.toString();
            }finally {
                try {
                    stmtupdate.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            result.put("success", false);
            result.put("message", "Book already exists");
            return result.toString();
        }

    }

    public String deletebook(String bookdetails) throws ClassNotFoundException, JSONException {
        System.out.println("eneted get book");
        Class.forName("com.mysql.jdbc.Driver");
        JSONObject result = new JSONObject();
        JSONObject update = new JSONObject(bookdetails);
        PreparedStatement stmtupdate = null;
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
            try {
                try {
                    String sql = "DELETE FROM library_books WHERE bookname = ? and id = ?";
                    stmtupdate = conn.prepareStatement(sql);
                    stmtupdate.setString(1, update.getString("book"));
                    stmtupdate.setString(2, update.getString("id"));
                    System.out.println(stmtupdate);
                    int records = stmtupdate.executeUpdate();
                    System.out.println(result);
                    if (records == 0) {
                        result.put("success", false);
                        result.put("message", "Failed to delete details");
                        return result.toString();
                    }
                    result.put("success", true);
                    result.put("message", "Book Details deleted successfully");
                    return result.toString();
                } catch (JSONException e) {
                    result.put("success", false);
                    result.put("message", "Failed to delete details");
                    return result.toString();
                }
            } catch (SQLException e) {
                System.out.println("entered");
                result.put("success", false);
                result.put("message", "Failed to delete details");
                return result.toString();
            }finally {
                try {
                    stmtupdate.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public String addbook(String bookdetails) throws ClassNotFoundException, JSONException {
        System.out.println("eneted get book " + bookdetails);
        String a ="";
        boolean empty = true;
        Class.forName("com.mysql.jdbc.Driver");
        PreparedStatement stmtupdate = null;
        JSONObject result = new JSONObject();
        JSONObject update = new JSONObject(bookdetails);
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "SELECT * FROM library_books where bookname =?;";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, update.getString("bookname"));
            System.out.println(stmt);
            ResultSet set = stmt.executeQuery();
            if(set.first()){
                System.out.println("enetered true");
                empty = false;
            }else{
                empty = true;
            }
        }catch (SQLException e) {
            System.out.println("entered");
            result.put("success", false);
            result.put("message", "Something went wrong...Please try again");
            return result.toString();
        }finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(empty) {
            try {
                try {
                    System.out.println("entered insert");
                    String sql = "INSERT INTO library_books (bookname,status,issuefrom,issuedto) VALUES (?,?,?,?)";
                    if(!update.getString("issuedto").equals("Pick the Email")){
                         a = update.getString("issuedto");
                    }
                    stmtupdate = conn.prepareStatement(sql);
                    stmtupdate.setString(1, update.getString("bookname"));
                    stmtupdate.setString(2, update.getString("status"));
                    stmtupdate.setString(3, update.getString("issuefrom"));
                    stmtupdate.setString(4, a);
                    System.out.println(stmtupdate);
                    int records = stmtupdate.executeUpdate();
                    System.out.println(result);
                    if (records == 0) {
                        result.put("success", false);
                        result.put("message", "Failed to add details");
                        return result.toString();
                    }
                    result.put("success", true);
                    result.put("message", "Book Details Updated successfully");
                    return result.toString();
                } catch (JSONException e) {
                    System.out.println("enetered json");
                    result.put("success", false);
                    result.put("message", "Failed to add details");
                    return result.toString();
                }
            } catch (SQLException e) {
                System.out.println("entered");
                result.put("success", false);
                result.put("message", "Failed to add details");
                return result.toString();
            }finally {
                try {
                    stmtupdate.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            result.put("success", false);
            result.put("message", "Book already exists");
            return result.toString();
        }

    }
}
