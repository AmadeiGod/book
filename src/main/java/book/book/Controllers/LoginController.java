package book.book.Controllers;

import book.book.DTO.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {


    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public String authenticateUser( LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*
        Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication2 instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication2.getName();
            System.out.println(authentication2.getName());
        }*/
        return "index";
    }


}
