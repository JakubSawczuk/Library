package wat.ai.services.books.dtos;
import java.util.Date;


public class BookDetails {
    private int bookId;
    private String isbn;
    private String author;
    private String titleEn;
    private String titlePL;
    private String description;
    private int editionNumber;
    private Date editionDate;
    private String editionPlace;
    private String genreName;
    private String publisherName;

    public  int getBookId() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
