package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.services.books.BookServiceImpl;
import wat.ai.services.books.dtos.AddBookDTO;
import wat.ai.services.books.dtos.BookBasicInfo;
import wat.ai.services.books.dtos.BookDetails;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger LOGGER = Logger.getLogger(BookController.class.getName());

    @Autowired
    BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<BookBasicInfo>> shareAllBooks() {
        List<BookBasicInfo> bookBasicInfoList = bookService.getAllBooks();
        return new ResponseEntity<>(bookBasicInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDetails> shareDetailsBook(
            @PathVariable("id") int bookId
    ) {
        BookDetails bookDetails = bookService.getBookDetails(bookId);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody AddBookDTO addBookDTO) {
        try {
            bookService.addBook(addBookDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody BookDetails bookDetails) {
        try {
            bookService.updateBook(bookDetails);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteBook(@RequestBody BookDetails bookDetails) {
        try {
            bookService.deleteBook(bookDetails);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bn")
    public ResponseEntity<List<BookDetails>> getFromBN(
            @RequestParam(value = "title", required = false) String titleParam,
            @RequestParam(value = "author", required = false) String authorParam,
            @RequestParam(value = "isbnIssn", required = false) String isbnParam
    ) {
        List<BookDetails> bookDetailsList = bookService.getBooksFromLN("title", titleParam, "author", authorParam, "isbnIssn", isbnParam);
        return new ResponseEntity<>(bookDetailsList, HttpStatus.OK);
    }
}
