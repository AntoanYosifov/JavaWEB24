package bg.sogtuni.mobilele24.web;

import bg.sogtuni.mobilele24.model.UserRegistrationDTO;
import bg.sogtuni.mobilele24.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public UserRegistrationDTO registerDTO(){
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register(){
        return "auth-register";
    }
    @PostMapping("/register")
    public String register(UserRegistrationDTO registrationDTO){
        this.userService.registerUser(registrationDTO);

        return "redirect:/";
    }
}
