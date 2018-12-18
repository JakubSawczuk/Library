package wat.ai.services.books;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.Book;
import wat.ai.nationalelibrary.JSONParser;
import wat.ai.repositories.BookRepository;
import wat.ai.services.books.dtos.BookBasicInfo;
import wat.ai.services.books.dtos.BookDetails;
import wat.ai.services.books.dtos.BookNL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    JSONParser jsonParser;

    @Override
    public List<BookBasicInfo> getAllBooks() {
        ModelMapper modelMapper = new ModelMapper();
        List<Book> allBooksList = bookRepository.findByIsActive(true);
        List<BookBasicInfo> booksBasicInfoList = new ArrayList<>();

        allBooksList.forEach(book -> {
            BookBasicInfo bookBasicInfo = modelMapper.map(book, BookBasicInfo.class);
            booksBasicInfoList.add(bookBasicInfo);
        });

        return booksBasicInfoList;
    }

    @Override
    public BookDetails getBookDetails(int bookId) {
        Book book = bookRepository.findByBookId(bookId);
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(book, BookDetails.class);
    }

    @Override
    public Book addBook(BookDetails theBookDetails) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(theBookDetails, Book.class);

        try {
            bookRepository.save(book);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return book;
    }

    @Override
    public BookDetails updateBook(BookDetails bookDetails) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDetails, Book.class);
        try {
            bookRepository.save(book);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return bookDetails;
    }

    @Override
    public BookDetails deleteBook(BookDetails bookDetails) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDetails, Book.class);
        book.setActive(false);
        try {
            bookRepository.save(book);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return bookDetails;
    }

    @Override
    public List<BookDetails> getBooksFromLN(String ... requestParamArray) {
        HashMap<String, String> requestParamMap = new HashMap();

        for (int i = 0; i < requestParamArray.length; i+=2) {
            requestParamMap.put(requestParamArray[i], requestParamArray[i+1]);
        }
        List<BookNL> bookNLList = jsonParser.getBooksFromApi(requestParamMap);
        List<BookDetails> bookDetailsList = new ArrayList<>();

        bookNLList.forEach(bookNL -> {
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.createTypeMap(BookNL.class, BookDetails.class).addMappings(mapper -> {
                if(bookNL.getLanguage().equals("polski")) mapper.map(BookNL::getTitle, BookDetails::setTitlePL);
                else if(bookNL.getLanguage().equals("angielski")) mapper.map(BookNL::getTitle, BookDetails::setTitleEn);
                mapper.map(BookNL::getGenre, BookDetails::setGenreName);
                mapper.map(BookNL::getPlaceOfPublication, BookDetails::setEditionPlace);
            });
            BookDetails bookDetails = modelMapper.map(bookNL, BookDetails.class);
            bookDetails.setEditionDate(new Date(Integer.valueOf(bookNL.getPublicationYear()), 1,1));
            bookDetails.setActive(true);
            bookDetailsList.add(bookDetails);

        });
        return bookDetailsList;
    }


}
