package wat.ai.services.bookloans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.BookLoans;
import wat.ai.repositories.BookLoansRepository;
import wat.ai.services.bookloans.dtos.BookLoanDetails;

@Service
public class BookLoanServiceImpl implements IBookLoanService {

    private final BookLoansRepository bookLoansRepository;

    @Autowired
    public BookLoanServiceImpl(BookLoansRepository bookLoansRepository) {
        this.bookLoansRepository = bookLoansRepository;
    }

    @Override
    public BookLoanDetails addBookLoan(BookLoanDetails bookLoanDetails) {
        ModelMapper modelMapper = new ModelMapper();
        BookLoans bookLoans = modelMapper.map(bookLoanDetails, BookLoans.class);
        bookLoans.setReaderId(1);
        bookLoans.setLibrarianId(1);
        bookLoans.setBookLoanId(1);

        bookLoansRepository.save(bookLoans);

        return null;
    }
}
