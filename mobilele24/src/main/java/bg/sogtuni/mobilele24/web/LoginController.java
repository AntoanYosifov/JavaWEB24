package bg.sogtuni.mobilele24.web;

import bg.sogtuni.mobilele24.model.UserLoginDTO;
import bg.sogtuni.mobilele24.model.UserRegistrationDTO;
import bg.sogtuni.mobilele24.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO){
        this.userService.login(userLoginDTO);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(){
        this.userService.logout();

        return "redirect:/";
    }
}


