package wat.ai.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BOOK_LOANS")
public class BookLoans {

    @Column(name = "BOOK_LOAN_ID")
    @GeneratedValue
    @Id
    private int bookLoanId;

    @Column(name = "LOAN_DATE")
    private Timestamp loanDate;

    @Column(name = "PLANNED_DUE_DATE")
    private Timestamp plannedDueDate;

    @Column(name = "ACUTAL_DUE_DATE")
    private Timestamp actualDueDate;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "LIBRARIAN_ID")
    private Librarian librarian;

    @ManyToOne
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    public int getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(int bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public Timestamp getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Timestamp loanDate) {
        this.loanDate = loanDate;
    }

    public Timestamp getPlannedDueDate() {
        return plannedDueDate;
    }

    public void setPlannedDueDate(Timestamp plannedDueDate) {
        this.plannedDueDate = plannedDueDate;
    }

    public Timestamp getActualDueDate() {
        return actualDueDate;
    }

    public void setActualDueDate(Timestamp actualDueDate) {
        this.actualDueDate = actualDueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}
