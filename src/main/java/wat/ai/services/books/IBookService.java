package wat.ai.services.books;

import org.springframework.stereotype.Component;
import wat.ai.models.entities.Book;
import wat.ai.services.books.dtos.AddBookDTO;
import wat.ai.services.books.dtos.BookBasicInfo;
import wat.ai.services.books.dtos.BookDetails;

import java.util.List;

@Component
public interface IBookService {
     Book addBook(AddBookDTO addBookDTO);
     List<BookBasicInfo> getAllBooks();
     BookDetails getBookDetails(int bookId);
     BookDetails updateBook(BookDetails bookDetails);
     void deleteBook(int bookId);
     List<BookDetails> getBooksFromLN(String[] requestParamArray);
}
