package bg.sogtuni.mobilele24.service.impl;

import bg.sogtuni.mobilele24.model.UserLoginDTO;
import bg.sogtuni.mobilele24.model.UserRegistrationDTO;
import bg.sogtuni.mobilele24.model.entity.UserEntity;
import bg.sogtuni.mobilele24.repository.UserRepository;
import bg.sogtuni.mobilele24.service.CurrentUser;
import bg.sogtuni.mobilele24.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        this.userRepository.save(map(userRegistrationDTO));
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = this.userRepository.findByEmail(userLoginDTO.email())
                .orElse(null);

        if (userLoginDTO.password() == null || userEntity == null
                || userEntity.getPassword() == null) {
            return false;
        }

        boolean success = this.passwordEncoder.matches(userLoginDTO.password(), userEntity.getPassword());

        if (success) {
            this.currentUser.setLoggedIn(true);
            this.currentUser.setFullName(userEntity.getFirstName()+ " " + userEntity.getLastName());
        } else {
            this.currentUser.clean();
        }

        return false;
    }

    @Override
    public void logout() {
        this.currentUser.clean();
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity user = this.modelMapper.map(userRegistrationDTO, UserEntity.class);

        user.setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return user;
    }


}
