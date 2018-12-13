package wat.ai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.models.User;
import wat.ai.models.UserDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final static String allActiveUsersQuery = "SELECT u FROM User u";

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDTO> getUserDTOList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = (List<User>) entityManager.createQuery(allActiveUsersQuery).getResultList();

        for(User user : userList){
            UserDTO userDTO =  new UserDTO();
            System.out.println(user.getFirstName());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setCardNumber(user.getCardNumber());
            userDTO.setPesel(user.getPesel());
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }




}
