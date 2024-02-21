package book.book.Controllers;

import book.book.Models.User;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ControllerUser {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/123")
    public String loginPage(Model model) {
        return "123";
    }
    @GetMapping("/admin")
    public String AdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/personalpage")
    String personalpage(Authentication authentication, Model model) {
        User user = userService.findUserByUsername(authentication.getName());
        model.addAttribute("Email", user.getEmail());
        model.addAttribute("Role", user.getRole());
        return "user/personalpage";
    }
}
