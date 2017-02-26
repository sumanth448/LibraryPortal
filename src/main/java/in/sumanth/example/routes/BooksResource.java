package in.sumanth.example.routes;

import in.sumanth.example.dao.BookDAO;
import in.sumanth.example.database.HomeDatabase;
import in.sumanth.example.database.model.Book;
import io.dropwizard.hibernate.UnitOfWork;
import org.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by sumanth.reddy on 04/12/16.
 */
@Path("/home")
@Produces(MediaType.APPLICATION_JSON)
public class BooksResource {

    /**
     * The DAO object to manipulate employees.
     */
    private BookDAO bookDAO;

    /**
     * Constructor.
     *
     * @param bookDAO DAO object to manipulate employees.
     */
    public BooksResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    /**
     * Looks for employees whose first or last name contains the passed
     * parameter as a substring. If name argument was not passed, returns all
     * employees stored in the database.
     *
     * @param name query parameter
     * @return list of employees whose first or last name contains the passed
     * parameter as a substring or list of all employees stored in the database.
     */
    @GET
    @Path("/get")
    @UnitOfWork
    public List<Book> findByName(
            @QueryParam("name") Optional<String> name
    ) {
        if (name.isPresent()) {
            return bookDAO.findByName(name.get());
        } else {
            return bookDAO.findAll();
        }
    }

    /*@GET
    public String sayHello() throws JSONException, SQLException, ClassNotFoundException {
        return new HomeDatabase().getbooks();
    }*/
    @GET
    @Path(value = "/getemaildetails/")
    public String getemaildetails() throws JSONException, SQLException, ClassNotFoundException {
        System.out.println("eneted emailldldsl");
        return new HomeDatabase().getemaildetails();
    }
    @POST
    @Path(value = "/updatebookdetails/")
    public String updatebookdetails(String bookdetails) throws JSONException, SQLException, ClassNotFoundException {
        System.out.println("eneted emailldldsl");
        return new HomeDatabase().updatebookdetails(bookdetails);
    }

    @POST
    @Path(value = "/deletebook/")
    public String deletebook(String bookdetails) throws JSONException, SQLException, ClassNotFoundException {
        System.out.println("eneted emailldldsl");
        return new HomeDatabase().deletebook(bookdetails);
    }

    @POST
    @Path(value = "/addbook/")
    public String addbook(String bookdetails) throws JSONException, SQLException, ClassNotFoundException {
        System.out.println("eneted emailldldsl");
        return new HomeDatabase().addbook(bookdetails);
    }
}
