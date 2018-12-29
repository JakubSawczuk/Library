package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.services.reports.ReportServiceImpl;

import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping
    @ResponseBody
    public ResponseEntity generateReport(HttpServletResponse response){
        try {
            reportService.generateReport(response);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
