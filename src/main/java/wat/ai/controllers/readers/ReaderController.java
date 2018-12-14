package wat.ai.controllers.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetails;
import wat.ai.services.readers.ReaderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    ReaderServiceImpl readerService;

    @GetMapping
    public ResponseEntity<List<ReaderBasicInfo>> shareAllActiveReader() {
        List<ReaderBasicInfo> readerBasicInfoList = readerService.getAllActiveUsers();
        return new ResponseEntity<>(readerBasicInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReaderDetails> shareDetalisReader(
            @PathVariable("id") int readerId
    ) {
        ReaderDetails readerDetails = readerService.getReaderDetails(readerId);
        return new ResponseEntity<>(readerDetails, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReaderDetails> updateReader(@RequestBody ReaderDetails readerDetails) {
        readerService.updateReader(readerDetails);
        return new ResponseEntity<>(readerDetails, HttpStatus.OK);
    }


}
