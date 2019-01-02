package wat.ai.securities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.securities.services.librarians.LibrarianServiceImpl;
import wat.ai.securities.services.librarians.dtos.LibrarianDetails;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/login")
public class LibrarianController {

    private static final Logger LOGGER = Logger.getLogger(LibrarianController.class.getName());

    @Autowired
    LibrarianServiceImpl librarianService;

    @PostMapping
    public ResponseEntity<LibrarianDetails> returnLoginInfo(@RequestBody UserLogin userLogin) {
        LibrarianDetails librarianDetails = librarianService.loginLibrarian(userLogin.getUsername(), userLogin.getPassword());

        if(librarianDetails == null)
            return new ResponseEntity<>(librarianDetails, HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(librarianDetails, HttpStatus.OK);
    }


}
