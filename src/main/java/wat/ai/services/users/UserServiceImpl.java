package wat.ai.services.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wat.ai.controllers.users.UserDTO;
import wat.ai.models.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final static String allActiveUsersQuery = "SELECT u FROM User u";

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDTO> getAllAtciveUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = (List<User>) entityManager.createQuery(allActiveUsersQuery).getResultList();
        ModelMapper modelMapper = new ModelMapper();

        userList.forEach(user -> {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        });

        return userDTOList;
    }




}
