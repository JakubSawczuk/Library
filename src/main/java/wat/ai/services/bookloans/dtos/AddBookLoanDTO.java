package wat.ai.services.bookloans.dtos;

import java.sql.Date;

public class AddBookLoanDTO {

    private int bookCopyId;
    private int librarianId;
    private int readerId;
    private Date loanDate;
    private Date plannedDueDate;

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
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
}
