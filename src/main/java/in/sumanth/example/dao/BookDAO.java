package in.sumanth.example.dao;

import in.sumanth.example.database.model.Book;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Optional;

/**
 * Created by sumanth.reddy on 16/02/17.
 */
public class BookDAO  extends AbstractDAO<Book> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Method returns all employees stored in the database.
     *
     * @return list of all employees stored in the database
     */
    public List<Book> findAll() {
        return list(namedQuery("in.sumanth.example.database.model.Book.findAll"));
    }

    /**
     * Looks for employees whose first or last name contains the passed
     * parameter as a substring.
     *
     * @param name query parameter
     * @return list of employees whose first or last name contains the passed
     * parameter as a substring.
     */
    public List<Book> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("in.sumanth.example.database.model.Book.findByName")
                        .setParameter("bookName", builder.toString())
        );
    }

    /**
     * Method looks for an employee by her id.
     *
     * @param id the id of an employee we are looking for.
     * @return Optional containing the found employee or an empty Optional
     * otherwise.
     */
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(get(id));
    }

}
