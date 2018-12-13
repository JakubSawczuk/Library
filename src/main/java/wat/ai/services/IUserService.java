package wat.ai.services;

import org.springframework.stereotype.Service;
import wat.ai.models.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getUserDTOList();
}
