package in.sumanth.example.database.model;

import javax.persistence.*;

/**
 * Created by sumanth.reddy on 27/01/17.
 */
@Entity
@Table(name = "library_books")
public class Book {

    /*result.put("id",set.getString("id"));
    result.put("book",set.getString("bookname"));
    result.put("date",set.getString("updated_at"));
    result.put("status",set.getString("status"));
    result.put("issuedfrom",set.getString("issuefrom"));
    result.put("issuedto",set.getString("issuedto"));*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * book name.
     */
    @Column(name = "bookname")
    private String bookName;

    /**
     * updated_at.
     */
    @Column(name = "updated_at")
    private String updated_at;

    /**
     * updated_at.
     */
    @Column(name = "status")
    private String status;

    /**
     * updated_at.
     */
    @Column(name = "issuefrom")
    private String issueFrom;

    /**
     * updated_at.
     */
    @Column(name = "issuedto")
    private String issuedTo;


    public Book(String bookName, String updated_at, String status, String issuefrom, String issuedto) {

        this.bookName = bookName;
        this.updated_at = updated_at;
        this.status = status;
        this.issueFrom = issuefrom;
        this.issuedTo = issuedto;
    }

}
