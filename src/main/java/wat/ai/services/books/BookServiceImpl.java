package wat.ai.services.books;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.Book;
import wat.ai.repositories.BookRepository;
import wat.ai.services.books.dtos.AddBookDTO;
import wat.ai.services.books.dtos.BookBasicInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements IBookService {
    static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class.getName());
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookBasicInfo> getAllBooks() {
        ModelMapper modelMapper = new ModelMapper();
        List<Book> allBooksList = (List<Book>) bookRepository.findAll();
        List<BookBasicInfo> booksBasicInfoList  = new ArrayList<>();

        allBooksList.forEach(book -> {
            BookBasicInfo bookBasicInfo = modelMapper.map(book, BookBasicInfo.class);
            booksBasicInfoList.add(bookBasicInfo);
        });

        return booksBasicInfoList;
    }

    @Override
    public AddBookDTO getBookDetails(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(book, AddBookDTO.class);
    }

    @Override
    public Book addBook(AddBookDTO theAddBookDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(theAddBookDTO, Book.class);

        try {
            bookRepository.save(book);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return book;
    }
}
