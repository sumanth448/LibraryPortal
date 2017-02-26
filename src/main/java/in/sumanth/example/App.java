package in.sumanth.example;

import in.sumanth.example.dao.BookDAO;
import in.sumanth.example.database.model.Book;
import in.sumanth.example.routes.BooksResource;
import in.sumanth.example.routes.loginroutes;
import in.sumanth.example.routes.registerroutes;
import io.dropwizard.Application;
import in.sumanth.example.config.ExampleServiceConfiguration;
import in.sumanth.example.routes.HelloResource;
import io.dropwizard.assets.AssetsBundle;
//import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App extends Application<ExampleServiceConfiguration>
{

    public static void main( String[] args ) throws Exception {
        String configPath = System.getProperty("user.dir") + File.separator + "resources" +File.separator + "configuration.yml";
        new App().run("server",configPath);
    }

    /*@Override
    public void initialize(Bootstrap<ExampleServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle());
    }*/

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<ExampleServiceConfiguration> hibernateBundle
            = new HibernateBundle<ExampleServiceConfiguration>(
            Book.class
    ) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                ExampleServiceConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };


    @Override
    public void initialize(
            final Bootstrap<ExampleServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(ExampleServiceConfiguration exampleServiceConfiguration, Environment environment) throws Exception {

        final BookDAO bookDAO
                = new BookDAO(hibernateBundle.getSessionFactory());

        //System.out.println(exampleServiceConfiguration.getpassword() + " " +exampleServiceConfiguration.getuser());
        //System.out.println(exampleServiceConfiguration.geturl() + " " +exampleServiceConfiguration.getDriver());
        //environment.jersey().register(new HelloResource());
        //environment.jersey().register(new registerroutes());
        //environment.jersey().register(new loginroutes());
        environment.jersey().register(new BooksResource(bookDAO));
    }
}
