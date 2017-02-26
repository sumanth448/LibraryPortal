package in.sumanth.example.routes;

import in.sumanth.example.database.RegisterDatabase;
import org.json.JSONException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
@Path(value="/register/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class registerroutes {
    @POST
    public String addUser(String user) throws JSONException, SQLException, ClassNotFoundException {
        System.out.println(user);
      return new RegisterDatabase().createuser(user);
    }

}
