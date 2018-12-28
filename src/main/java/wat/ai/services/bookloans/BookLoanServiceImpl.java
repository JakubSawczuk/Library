package wat.ai.services.bookloans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.BookLoans;
import wat.ai.repositories.BookLoansRepository;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;

@Service
public class BookLoanServiceImpl implements IBookLoanService {

    private final BookLoansRepository bookLoansRepository;

    @Autowired
    public BookLoanServiceImpl(BookLoansRepository bookLoansRepository) {
        this.bookLoansRepository = bookLoansRepository;
    }

    @Override
    public void addBookLoan(AddBookLoanDTO addBookLoanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        BookLoans bookLoans = modelMapper.map(addBookLoanDTO, BookLoans.class);
        bookLoans.setStatus("BORROWED");

        bookLoansRepository.save(bookLoans);
    }
}
