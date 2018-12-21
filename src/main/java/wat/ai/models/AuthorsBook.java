package wat.ai.models;

import javax.persistence.*;

//@Entity(name = "AUTHORS_BOOK")
public class AuthorsBook {

    @GeneratedValue
    @Id
    @Column(name = "AUTHOR_BOOK_ID")
    private int AuthorBookId;

    @ManyToOne
    @JoinColumn(name = "BOOK")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public int getAuthorBookId() {
        return AuthorBookId;
    }

    public void setAuthorBookId(int authorBookId) {
        AuthorBookId = authorBookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
