package bg.sogtuni.mobilele24.service;

import bg.sogtuni.mobilele24.model.UserLoginDTO;
import bg.sogtuni.mobilele24.model.UserRegistrationDTO;

public interface UserService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
