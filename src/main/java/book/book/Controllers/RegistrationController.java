package book.book.Controllers;

import book.book.DTO.RegDto;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    private UserService userService;

    @ModelAttribute("user")
    public RegDto userRegistrationDto() {
        return new RegDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                                          RegDto registrationDto) {

        try {
            userService.save(registrationDto);
        }catch(Exception e)
        {
            System.out.println(e);
            return "redirect:/registration?email_invalid";
        }
        return "redirect:/registration?success";
    }

}
