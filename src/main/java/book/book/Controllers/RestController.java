package book.book.Controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("user/info")
    String getGreetings(@AuthenticationPrincipal UserDetails userDetails) {
        String user_name = userDetails.getUsername();
        // Используем
        return "/user/info";
    }
    @GetMapping("user/info1")
    public String currentUserName(Principal principal) {
        return principal.getName;
    }
}
