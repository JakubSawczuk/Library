package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.services.readers.ReaderServiceImpl;
import wat.ai.services.readers.dtos.AddReaderDTO;
import wat.ai.services.readers.dtos.BookLoansReaderInfo;
import wat.ai.services.readers.dtos.ReaderBasicInfo;
import wat.ai.services.readers.dtos.ReaderDetails;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    private static final Logger LOGGER = Logger.getLogger(ReaderController.class.getName());

    @Autowired
    ReaderServiceImpl readerService;

    @GetMapping
    public ResponseEntity<List<ReaderBasicInfo>> shareAllActiveReader() {
        List<ReaderBasicInfo> readerBasicInfoList = readerService.getAllActiveUsers();
        return new ResponseEntity<>(readerBasicInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReaderDetails> shareDetailsReader(
            @PathVariable("id") int readerId
    ) {
        ReaderDetails readerDetails = readerService.getReaderDetails(readerId);
        return new ResponseEntity<>(readerDetails, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateReader(@RequestBody ReaderDetails readerDetails) {
        try {
            readerService.updateReader(readerDetails);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(readerDetails, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteReader(
            @PathVariable("id") int readerId
    ) {
        try {
            readerService.deleteReader(readerId);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createReader(@RequestBody AddReaderDTO addReaderDTO) {
        try {
            readerService.addReader(addReaderDTO);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/lov")
    public ResponseEntity<List<BookLoansReaderInfo>> shareAllReaderToLoan() {
        List<BookLoansReaderInfo> bookLoansReaderInfoList = readerService.readerInfoToLoans();
        return new ResponseEntity<>(bookLoansReaderInfoList, HttpStatus.OK);
    }
}
