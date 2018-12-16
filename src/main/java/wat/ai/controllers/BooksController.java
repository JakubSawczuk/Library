package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.services.books.BookServiceImpl;
import wat.ai.services.books.dtos.AddBookDTO;
import wat.ai.services.books.dtos.BookBasicInfo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private static final Logger LOGGER = Logger.getLogger(BooksController.class.getName());

    @Autowired
    BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<BookBasicInfo>> shareAllBooks() {
        List<BookBasicInfo> bookBasicInfoList = bookService.getAllBooks();
        return new ResponseEntity<>(bookBasicInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddBookDTO> shareDetailsReader(
            @PathVariable("id") int bookId
    ) {
        AddBookDTO addBookDTO = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(addBookDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody AddBookDTO addBookDTO) {
        try {
            bookService.addBook(addBookDTO);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
