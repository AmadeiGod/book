package book.book.Controllers;

import book.book.DTO.RegDto;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
            RegDto regDto = new RegDto();
            model.addAttribute("user", regDto);
            return "registration";}
    @ModelAttribute("user")
    public RegDto userRegistrationDto() {
        return new RegDto();
    }

    @PostMapping("registration")
    public String registerUserAccount(RegDto registrationDto) {

        if(userRepository.existsByUsername(registrationDto.getUsername())){
            System.out.println("Такой логин уже есть");
            return "registration";
        }

        if(userRepository.existsByEmail(registrationDto.getEmail())){
            System.out.println("Такой email уже есть");
            return "registration";
        }

        try {
            userService.save(registrationDto);
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Ошибка регистрации");
            return "redirect:/registration?email_invalid";
        }
        System.out.println("User is registered successfully!");
        return "login";
    }

}
