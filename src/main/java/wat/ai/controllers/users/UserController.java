package wat.ai.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wat.ai.services.users.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    public UserController() {
    }


    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> shareAllActiveUsers() {
        List<UserDTO> userListDTO = userService.getAllAtciveUsers();

        return new ResponseEntity<>(userListDTO, HttpStatus.OK);
    }
}
