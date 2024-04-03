package book.book.Controllers;

import book.book.DTO.RegDto;
import book.book.Models.ConfirmationToken;
import book.book.Models.User;
import book.book.Repo.ConfirmationTokenRepository;
import book.book.Repo.UserRepository;
import book.book.Service.EmailSenderService;
import book.book.Service.UserService;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    private final UserService userService;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    private JavaMailSender mailSender;

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
            User user = userService.findUserByUsername(registrationDto.getUsername());

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom(String.valueOf(new InternetAddress("daudmammaev@yandex.ru")));
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);


            /*SimpleMailMessage email = new SimpleMailMessage();
            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            email.setFrom(String.valueOf(new InternetAddress("daudmammaev@yandex.ru")));
            email.setTo(recipientAddress);
            email.setText("blabla");
            email.setSubject(subject);
            mailSender.send(email);*/

        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Ошибка регистрации");
            return "redirect:/registration?email_invalid";
        }
        System.out.println("Пользователь зарегистрирован    ");
        return "/auth/login";
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            System.out.println("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            System.out.println("error");
        }

        return "/auth/login";
    }

}
