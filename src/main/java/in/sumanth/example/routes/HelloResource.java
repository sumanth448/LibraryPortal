package in.sumanth.example.routes;
import org.json.JSONException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.sql.SQLException;

/**
 * Created by sumanth.reddy on 01/12/16.
 */
@Path(value = "/routes/{name}")
public class HelloResource {
    @GET
    public String sayHello(@PathParam("name") String name) throws JSONException, SQLException, ClassNotFoundException {
        String x =new databaseconnection().getdetailsbyID("Sandy");
        return x;

    }

}
