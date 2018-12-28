package wat.ai.services.bookloans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.BookCopy;
import wat.ai.models.BookLoans;
import wat.ai.repositories.BookCopyRepository;
import wat.ai.repositories.BookLoansRepository;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;

@Service
public class BookLoanServiceImpl implements IBookLoanService {

    private final BookLoansRepository bookLoansRepository;

    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookLoanServiceImpl(BookLoansRepository bookLoansRepository, BookCopyRepository bookCopyRepository) {
        this.bookLoansRepository = bookLoansRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    @Override
    public void addBookLoan(AddBookLoanDTO addBookLoanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        BookLoans bookLoans = modelMapper.map(addBookLoanDTO, BookLoans.class);
        bookLoans.setStatus("BORROWED");
        BookCopy bookCopy = bookCopyRepository.findByBookCopyId(addBookLoanDTO.getBookCopyId()).get(0);
        bookCopy.setAvailable(false);

        bookCopyRepository.save(bookCopy);
        bookLoansRepository.save(bookLoans);
    }
}
