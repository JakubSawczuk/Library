package wat.ai.services.bookloans.dtos;

import wat.ai.models.Librarian;
import wat.ai.models.Reader;

import java.sql.Timestamp;

public class BookLoanDetails {

    private Timestamp loanDate;
    private Timestamp plannedDueDate;
    private Timestamp actualDueDate;
    private String status;
    private Reader reader;
    private Librarian librarian;
    private int bookId;

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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
