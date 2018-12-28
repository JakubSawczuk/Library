package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.services.bookcopies.BookCopyImpl;
import wat.ai.services.bookcopies.dtos.BookCopyDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopyController {
    private static final Logger LOGGER = Logger.getLogger(BookCopyController.class.getName());

    @Autowired
    BookCopyImpl bookCopyService;

    @GetMapping
    public ResponseEntity<List<BookCopyDTO>> shareAllBookCopies() {
        List<BookCopyDTO> bookCopyDTOList = bookCopyService.getAllBookCopies();
        return new ResponseEntity<>(bookCopyDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<BookCopyDTO>> shareBookCopiesForBook(
            @PathVariable("id") int bookId
    ) {
        List<BookCopyDTO> bookCopyDTOList = bookCopyService.getBookCopiesForBook(bookId);
        return new ResponseEntity<>(bookCopyDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/book-copy/{bookCopyId}")
    public ResponseEntity<BookCopyDTO> shareDetailsBookCopy(
            @PathVariable("bookCopyId") int bookCopyId
    ) {
        BookCopyDTO bookCopyDTO = bookCopyService.getBookCopyDetails(bookCopyId);
        return new ResponseEntity<>(bookCopyDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBookCopy(@RequestBody BookCopyDTO bookCopyDTO) {
        try {
            bookCopyService.addBookCopy(bookCopyDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateBookCopy(@RequestBody BookCopyDTO bookCopyDTO) {
        try {
            bookCopyService.updateBookCopy(bookCopyDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBookCopy(
            @PathVariable("id") int bookCopyId
    ) {
        try {
            bookCopyService.deleteBookCopy(bookCopyId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
