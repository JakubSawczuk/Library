package wat.ai.services.bookloans.dtos;

import java.sql.Date;

public class BookLoanDetails {

    private int bookLoanId;
    private Date loanDate;
    private Date plannedDueDate;
    private Date actualDueDate;
    private String status;
    private String title;
    private String copyNumber;

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

    public int getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(int bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }
}
