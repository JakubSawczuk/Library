package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.services.bookloans.BookLoanServiceImpl;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;
import wat.ai.services.bookloans.dtos.BookLoanDetails;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/bookloans")
public class BookLoanController {
    private static final Logger LOGGER = Logger.getLogger(BookLoanController.class.getName());

    @Autowired
    BookLoanServiceImpl bookLoanService;

    @PostMapping
    public ResponseEntity createBookLoan(@RequestBody AddBookLoanDTO addBookLoanDTO) {
        try {
            bookLoanService.addBookLoan(addBookLoanDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{readerId}/{status}")
    public ResponseEntity<List<BookLoanDetails>> getBookLoansWithDetail(
            @PathVariable("readerId") int readerId,
            @PathVariable("status") String status
    ) {
        List<BookLoanDetails> bookLoanDetailsList = null;
        try {
            bookLoanDetailsList = bookLoanService.getAllBookLoansWithDetails(readerId,status);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookLoanDetailsList,HttpStatus.OK);
    }

    @PutMapping(value = "/{bookLoanId}")
    public ResponseEntity changeStatus(
            @PathVariable("bookLoanId") int bookLoanId
    ) {
        try {
            bookLoanService.changeStatus(bookLoanId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
