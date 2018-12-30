package wat.ai.services.bookloans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.entities.BookCopy;
import wat.ai.models.entities.BookLoans;
import wat.ai.models.entities.Reader;
import wat.ai.repositories.BookCopyRepository;
import wat.ai.repositories.BookLoanRepository;
import wat.ai.repositories.ReaderRepository;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;
import wat.ai.services.bookloans.dtos.BookLoanDetails;
import wat.ai.services.bookloans.enums.BookLoansStatusE;
import wat.ai.threads.Mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class BookLoanServiceImpl implements IBookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final BookCopyRepository bookCopyRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public BookLoanServiceImpl(BookLoanRepository bookLoanRepository, BookCopyRepository bookCopyRepository, ReaderRepository readerRepository) {
        this.bookLoanRepository = bookLoanRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.readerRepository = readerRepository;
    }

    @Override
    public void addBookLoan(AddBookLoanDTO addBookLoanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        BookLoans bookLoans = modelMapper.map(addBookLoanDTO, BookLoans.class);
        bookLoans.setStatus(BookLoansStatusE.BORROWED.getDesc());
        BookCopy bookCopy = bookCopyRepository.findByBookCopyId(addBookLoanDTO.getBookCopyId()).get(0);
        bookCopy.setAvailable(false);

        bookCopyRepository.save(bookCopy);
        bookLoanRepository.save(bookLoans);

        sendMail(bookLoans, bookCopy);
    }

    private void sendMail(BookLoans bookLoans, BookCopy bookCopy) {
        Reader reader = readerRepository.findByReaderId(bookLoans.getReader().getReaderId());
        Runnable mail = new Mail(reader.getEmail(), Integer.toString(bookLoans.getBookLoanId()), bookCopy.getBook().getTitlePL(),
                reader.getFirstName() + " " + reader.getLastName(), bookLoans.getPlannedDueDate());
        new Thread(mail).start();
    }

    @Override
    public List<BookLoanDetails> getAllBookLoansWithDetails(int readerId, String status) {
        ModelMapper modelMapper = new ModelMapper();
        List<BookLoans> repositoryBookLoans = bookLoanRepository.findByReaderReaderIdAndStatus(readerId, status.toUpperCase());
        List<BookLoanDetails> bookLoanDetailsList = new ArrayList<>();

        repositoryBookLoans.forEach(bookLoans -> {
            BookLoanDetails bookLoanDetails = modelMapper.map(bookLoans, BookLoanDetails.class);
            bookLoanDetails.setTitle(bookLoans.getBookCopy().getBook().getTitlePL());
            if (bookLoans.getBookCopy().getBook().getTitleEn() != null) {
                bookLoanDetails.setTitle(bookLoanDetails.getTitle() + " (" + bookLoans.getBookCopy().getBook().getTitleEn() + ")");
            }
            bookLoanDetailsList.add(bookLoanDetails);
        });

        return bookLoanDetailsList;
    }

    @Override
    public void changeStatus(int bookLoanId) {
        BookLoans bookLoans = bookLoanRepository.findByBookLoanId(bookLoanId);
        bookLoans.setStatus(BookLoansStatusE.RETURNED.getDesc());
        bookLoans.setActualDueDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        BookCopy bookCopy = bookCopyRepository.findByBookCopyId(bookLoans.getBookCopy().getBookCopyId()).get(0);
        bookCopy.setAvailable(true);

        bookLoanRepository.save(bookLoans);
    }
}
