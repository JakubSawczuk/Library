package wat.ai.services.users;

import wat.ai.controllers.users.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllAtciveUsers();
}
