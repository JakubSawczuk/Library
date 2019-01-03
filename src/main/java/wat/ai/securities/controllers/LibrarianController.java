package wat.ai.securities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wat.ai.securities.services.librarians.LibrarianServiceImpl;
import wat.ai.securities.services.librarians.dtos.LibrarianDetails;
import wat.ai.security2.dtos.UserLogin;

import java.util.logging.Logger;

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
