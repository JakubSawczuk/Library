package wat.ai.services.bookloans;

import wat.ai.services.bookloans.dtos.AddBookLoanDTO;
import wat.ai.services.bookloans.dtos.BookLoanDetails;

import java.util.List;

public interface IBookLoanService {

    void addBookLoan(AddBookLoanDTO addBookLoanDTO);

    List<BookLoanDetails> getAllBookLoansWithDetails(int readerId, String status);
}
