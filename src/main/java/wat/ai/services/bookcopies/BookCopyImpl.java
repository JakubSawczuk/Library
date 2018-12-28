package wat.ai.services.bookcopies;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.BookCopy;
import wat.ai.repositories.BookCopyRepository;
import wat.ai.services.bookcopies.dtos.BookCopyDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookCopyImpl implements IBookCopy {

    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookCopyImpl(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopyDTO> getAllBookCopies() {
        return getBookCopies("isAvailable", null, null);
    }

    @Override
    public void addBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = addOrUpdate(bookCopyDTO, "add");
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public List<BookCopyDTO> getBookCopiesForBook(int bookId) {
        return getBookCopies("bookId", bookId, null);
    }

    @Override
    public BookCopyDTO getBookCopyDetails(int bookCopyId) {
        return getBookCopies("bookCopyId", null, bookCopyId).get(0);
    }

    @Override
    public void updateBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = addOrUpdate(bookCopyDTO, "update");
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public void deleteBookCopy(int bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.findByBookCopyId(bookCopyId).get(0);
        bookCopy.setAvailableAndActive(false, false);
        bookCopyRepository.save(bookCopy);
    }

    public BookCopy addOrUpdate(BookCopyDTO bookCopyDTO, String operation) {
        ModelMapper modelMapper = new ModelMapper();
        BookCopy bookCopy = modelMapper.map(bookCopyDTO, BookCopy.class);
        if (operation.equals("add")) {
            bookCopy.setBookCopyId(0);
            bookCopy.setAvailableAndActive(true, true);
        }else if(operation.equals("update")){
            bookCopy.setActive(true);
        }
        return bookCopy;
    }

    private List<BookCopyDTO> getBookCopies(String findBy, Integer bookId, Integer bookCopyId) {
        List<BookCopyDTO> bookCopyDTOList = new ArrayList<>();
        List<BookCopy> bookCopies = null;

        if (findBy.equals("bookId")) {
            bookCopies = bookCopyRepository.findByBookId(bookId);
        } else if (findBy.equals("isAvailable")) {
            bookCopies = bookCopyRepository.findByIsAvailable(true);
        } else if (findBy.equals("bookCopyId")) {
            bookCopies = bookCopyRepository.findByBookCopyId(bookCopyId);
        }

        bookCopies.forEach(bookCopy -> {
            BookCopyDTO bookCopyDTO = new BookCopyDTO();
            bookCopyDTO.setBookCopyId(bookCopy.getBookCopyId());
            bookCopyDTO.setBookId(bookCopy.getBook().getBookId());
            bookCopyDTO.setCopyNumber(bookCopy.getCopyNumber());
            bookCopyDTO.setDescription(bookCopy.getDescription());
            bookCopyDTO.setLocation(bookCopy.getLocation());
            bookCopyDTO.setAvailable(bookCopy.isAvailable());
            bookCopyDTOList.add(bookCopyDTO);
        });

        return bookCopyDTOList;
    }
}
