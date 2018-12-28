package wat.ai.models;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "BOOK_LOANS")
public class BookLoans {

    @Column(name = "BOOK_LOAN_ID")
    @GeneratedValue
    @Id
    private int bookLoanId;

    @Column(name = "LOAN_DATE")
    private Date loanDate;

    @Column(name = "PLANNED_DUE_DATE")
    private Date plannedDueDate;

    @Column(name = "ACUTAL_DUE_DATE")
    private Date actualDueDate;

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

    public void setReaderId(int readerId){
        reader.setReaderId(readerId);
    }

    public void setLibrarianId(int librarianId){
        librarian.setLibrarianId(librarianId);
    }

    public void setBookCopyId(int bookCopyId){
        bookCopy.setBookCopyId(bookCopyId);
    }

    public int getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(int bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getPlannedDueDate() {
        return plannedDueDate;
    }

    public void setPlannedDueDate(Date plannedDueDate) {
        this.plannedDueDate = plannedDueDate;
    }

    public Date getActualDueDate() {
        return actualDueDate;
    }

    public void setActualDueDate(Date actualDueDate) {
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
