package wat.ai.services.bookloans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.BookCopy;
import wat.ai.models.BookLoans;
import wat.ai.repositories.BookCopyRepository;
import wat.ai.repositories.BookLoansRepository;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;
import wat.ai.services.bookloans.dtos.BookLoanDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<BookLoanDetails> getAllBookLoansWithDetails(int readerId, String status) {
        List<Object[]> queryObjects = bookLoansRepository.findByReaderIdAndStatus(readerId, status.toUpperCase());
        return getAllValuesFromArray(queryObjects);
    }

    private List<BookLoanDetails> getAllValuesFromArray(List<Object[]> queryObjects){
        List<BookLoanDetails> bookLoanDetailsList = new ArrayList<>();
        for (Object[] o : queryObjects) {
            BookLoanDetails bookLoanDetails = new BookLoanDetails();
            bookLoanDetails.setBookLoanId((Integer) o[0]);
            bookLoanDetails.setCopyNumber((String) o[1]);
            bookLoanDetails.setTitlePl((String) o[2]);
            bookLoanDetails.setTitleEn((String) o[3]);
            bookLoanDetails.setLoanDate((Date) o[4]);
            bookLoanDetails.setPlannedDueDate((Date) o[5]);
            bookLoanDetails.setActualDueDate((Date) o[6]);
            bookLoanDetails.setStatus((String) o[7]);
            bookLoanDetailsList.add(bookLoanDetails);
        }
        return bookLoanDetailsList;
    }
}
