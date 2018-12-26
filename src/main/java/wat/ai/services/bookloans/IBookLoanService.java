package wat.ai.services.bookloans;

import wat.ai.services.bookloans.dtos.BookLoanDetails;

public interface IBookLoanService {

    BookLoanDetails addBookLoan(BookLoanDetails bookLoanDetails);
}
