package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.models.views.BookLoansByGenre_V;
import wat.ai.services.graphs.GraphServiceImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/graphs")
public class GraphController {
    private static final Logger LOGGER = Logger.getLogger(GraphController.class.getName());

    @Autowired
    GraphServiceImpl graphService;

    @GetMapping(value = "/BookLoansByGenre_V")
    public ResponseEntity<List<BookLoansByGenre_V>> getBookLoansByGenre_V() {
        try {
            return new ResponseEntity<>(graphService.getBookLoansByGenre_V(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
