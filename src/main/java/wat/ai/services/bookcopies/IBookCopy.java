package wat.ai.services.bookcopies;

import wat.ai.services.bookcopies.dtos.BookCopyDTO;

import java.util.List;

public interface IBookCopy {
    void addBookCopy(BookCopyDTO bookCopyDTO);
    List<BookCopyDTO> getBookCopiesForBook(int bookId);
    List<BookCopyDTO> getBookCopyDetails(int bookCopyId);
    void updateBookCopy(BookCopyDTO bookCopyDTO);
    void deleteBookCopy(BookCopyDTO bookCopyDTO);
}
