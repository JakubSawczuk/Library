package wat.ai.services.bookcopies;

import wat.ai.services.bookcopies.dtos.BookCopyDTO;

public interface IBookCopy {
    void addBookCopy(BookCopyDTO bookCopyDTO);
    BookCopyDTO getBookCopyDetails(int bookCopyId);
    void updateBookCopy(BookCopyDTO bookCopyDTO);
    void deleteBookCopy(BookCopyDTO bookCopyDTO);
}
