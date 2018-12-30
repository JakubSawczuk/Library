package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.models.views.TopReaders_V;
import wat.ai.services.statisticts.StatisticServiceImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    private static final Logger LOGGER = Logger.getLogger(StatisticController.class.getName());

    @Autowired
    StatisticServiceImpl statisticService;

    @GetMapping(value = "/TopReaders_V")
    public ResponseEntity<List<TopReaders_V>> getBookLoansByGenre_V() {
        try {
            return new ResponseEntity<>(statisticService.getTopReaders_V(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
