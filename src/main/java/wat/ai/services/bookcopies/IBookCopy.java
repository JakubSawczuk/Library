package wat.ai.services.bookcopies;

import wat.ai.services.bookcopies.dtos.BookCopyDTO;

import java.util.List;

public interface IBookCopy {
    void addBookCopy(BookCopyDTO bookCopyDTO);
    List<BookCopyDTO> getBookCopyDetails(int bookId);
    void updateBookCopy(BookCopyDTO bookCopyDTO);
    void deleteBookCopy(BookCopyDTO bookCopyDTO);
}
