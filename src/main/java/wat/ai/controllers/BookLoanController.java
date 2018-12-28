package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.services.bookloans.BookLoanServiceImpl;
import wat.ai.services.bookloans.dtos.AddBookLoanDTO;

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
}
