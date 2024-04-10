package book.book.Controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }



}
