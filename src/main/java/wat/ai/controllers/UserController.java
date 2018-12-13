package wat.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import wat.ai.models.User;
import wat.ai.models.UserDTO;
import wat.ai.services.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    UserServiceImpl userService;

    @Autowired
    EntityManager entityManager;


    public UserController() {
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> transferToLocalhost() {

        List<UserDTO> userListDTO = userService.getUserDTOList();

/*        User user = new User();
        user.setFirstName("Kuba");
        user.setLastName("Sawczuk");
        user.setCardNumber("Bedzie 5 z AI");
        user.setPesel("13122018");

        User user2 = new User();
        user2.setFirstName("Kamil");
        user2.setLastName("Rybicki");
        user2.setCardNumber("Bedzie 4,5 z AI");
        user2.setPesel("13122019");*/


        try {

            entityManager.getTransaction().begin();
/*            entityManager.persist(user);
            entityManager.persist(user2);*/
            entityManager.getTransaction().commit();

        } catch (Exception e) {

        }

        return new ResponseEntity<>(userListDTO, HttpStatus.OK);
    }
}
