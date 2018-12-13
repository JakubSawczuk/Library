package wat.ai.controllers.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.controllers.readers.dtos.ReaderBasicInfo;
import wat.ai.controllers.readers.dtos.ReaderDetalis;
import wat.ai.services.readers.ReaderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    @Autowired
    ReaderServiceImpl userService;

    public ReaderController() {
    }


    @GetMapping
    public ResponseEntity<List<ReaderBasicInfo>> shareAllActiveUsers() {
        List<ReaderBasicInfo> readerBasicInfoList = userService.getAllAtciveUsers();
        return new ResponseEntity<>(readerBasicInfoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReaderDetalis> shareAllActiveUsers(
            @PathVariable("id") int readerId
    ) {

        ReaderDetalis readerDetalis = userService.getUserDetails(readerId);
        return new ResponseEntity<>(readerDetalis, HttpStatus.OK);
    }
}
