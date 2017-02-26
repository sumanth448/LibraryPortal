package in.sumanth.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by sumanth.reddy on 01/12/16.
 */
    public class ExampleServiceConfiguration extends Configuration {

    private String url;
    private String user;
    private String password;
    private String driver;

//    @JsonProperty("driverClass")
//    public String geturl() {
//        return url;
//    }
//
//    @JsonProperty("user")
//    public String getuser() {
//        return user;
//    }
//
//    @JsonProperty("password")
//    public String getpassword() {
//        return password;
//    }
//
//    @Valid
//    @NotNull
//    @JsonProperty("dbUrl")
//    public String getDriver() {
//        return driver;
//    }
//}


/**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration
     * parameters such as database connection driver, URI, password etc.
     */

    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();


/**
     * A getter for the database factory.
     *
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

}



