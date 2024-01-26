package book.book.Controllers;

import book.book.DTO.RegDto;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class RegistrationController {

    private UserRepository userRepository;
    private UserService userService;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @ModelAttribute("user")
    public RegDto userRegistrationDto() {
        return new RegDto();
    }

    @PostMapping("registration")
    public String registerUserAccount(@ModelAttribute("user")  RegDto registrationDto) {

        if(userRepository.existsByUsername(registrationDto.getUsername())){
            return String.valueOf(new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST));
        }

        if(userRepository.existsByEmail(registrationDto.getEmail())){
            return String.valueOf(new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST));
        }

        try {
            userService.save(registrationDto);
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Ошибка регистрации");
            return "redirect:/registration?email_invalid";
        }

        return String.valueOf(new ResponseEntity<>("User is registered successfully!", HttpStatus.OK));
    }

}
