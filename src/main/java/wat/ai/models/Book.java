package wat.ai.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "BOOK")
public class Book {

    @GeneratedValue
    @Id
    @Column(name = "BOOK_ID")
    private int bookId;

    @Column(name = "ISBN", length = 13, unique = true)
    private String isbn;

    @Column(name = "TITLE_EN")
    private String titleEn;

    @Column(name = "TITLE_PL")
    private String titlePL;

    @Column(name = "DESCRIPTION", length = 4000)
    private String description;

    @Column(name = "EDITION_NUMBER", unique = true)
    private int editionNumber;

    @Column(name = "EDITION_DATE")
    private Date editionDate;

    @Column(name = "EDITION_PLACE")
    private String editionPlace;

    @Column(name = "GENRE_NAME")
    private String genreName;

    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitlePL() {
        return titlePL;
    }

    public void setTitlePL(String titlePL) {
        this.titlePL = titlePL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }

    public String getEditionPlace() {
        return editionPlace;
    }

    public void setEditionPlace(String editionPlace) {
        this.editionPlace = editionPlace;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
