package book.book.Controllers;

import book.book.DTO.PersonDTO;
import book.book.Models.Person;
import book.book.Service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        PersonDTO personDto = new PersonDTO();
        model.addAttribute("user", personDto);
        return "registration";
    }
    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid PersonDTO personDto,
            HttpServletRequest request,
            Errors errors) {

        try {
            Person registered = PersonService.registerNewUserAccount(personDto);
        } catch (UserAlreadyExistException uaeEx) {
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("successRegister", "person", personDto);
    }
}