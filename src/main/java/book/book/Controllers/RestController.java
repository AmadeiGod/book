package book.book.Controllers;


import book.book.Models.User;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final UserService userService;
    @Autowired
    public RestTemplate restTemplate;
    public RestController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping("user/info1")
    public String currentUserName(Principal principal) {
        return null;
    }
}
