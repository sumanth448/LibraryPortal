package in.sumanth.example.routes;

import in.sumanth.example.database.LoginDatabase;
import org.json.JSONException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.sql.SQLException;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
@Path(value = "/login/{username}")
public class loginroutes {
@GET
public String sayHello(@PathParam("username") String username) throws JSONException, SQLException, ClassNotFoundException {
   return new LoginDatabase().getpassword(username);
}
}
