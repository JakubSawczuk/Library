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
        List<BookCopy> activeBookCopyList = bookCopyRepository.findByIsAvailable(true);
        List<BookCopyDTO> bookCopyDTOList = new ArrayList<>();

        activeBookCopyList.forEach(bookCopies -> {
            BookCopyDTO bookCopyDTO = new BookCopyDTO();
            bookCopyDTO.setBookId(bookCopies.getBook().getBookId());
            bookCopyDTO.setCopyNumber(bookCopies.getCopyNumber());
            bookCopyDTO.setDescription(bookCopies.getDescription());
            bookCopyDTO.setLocation(bookCopies.getLocation());

            bookCopyDTOList.add(bookCopyDTO);
        });

        return bookCopyDTOList;
    }

    @Override
    public void addBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = addOrUpdateOrDelete(bookCopyDTO, "add");
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public BookCopyDTO getBookCopyDetails(int bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.findByBookCopyId(bookCopyId);

        BookCopyDTO bookCopyDTO = new BookCopyDTO();
        bookCopyDTO.setBookId(bookCopy.getBook().getBookId());
        bookCopyDTO.setCopyNumber(bookCopy.getCopyNumber());
        bookCopyDTO.setDescription(bookCopy.getDescription());
        bookCopyDTO.setLocation(bookCopy.getLocation());

        return bookCopyDTO;
    }

    @Override
    public void updateBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = addOrUpdateOrDelete(bookCopyDTO, "update");
        bookCopyRepository.save(bookCopy);
    }

    @Override
    public void deleteBookCopy(BookCopyDTO bookCopyDTO) {
        BookCopy bookCopy = addOrUpdateOrDelete(bookCopyDTO, "delete");
        bookCopyRepository.save(bookCopy);
    }

    public BookCopy addOrUpdateOrDelete(BookCopyDTO bookCopyDTO, String operation){
        ModelMapper modelMapper = new ModelMapper();
        BookCopy bookCopy = modelMapper.map(bookCopyDTO, BookCopy.class);
        bookCopy.setAvailable(true);
        if(operation.equals("add")){
            bookCopy.setBookCopyId(0);
        } else if (operation.equals("delete")) {
            bookCopy.setAvailable(false);
        }
        return bookCopy;
    }
}
