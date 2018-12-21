package wat.ai.models;

import javax.persistence.*;

@Entity(name = "BOOK_COPY")
public class BookCopy {

    @GeneratedValue
    @Id
    @Column(name = "BOOK_COPY_ID")
    private int bookCopyId;

    @Column(name = "COPY_NUMBER", length = 9)
    private String copyNumber;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_AVAIALBE")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public void setBookId(int bookId){
        book.setBookId(bookId);
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
