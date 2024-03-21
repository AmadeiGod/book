package book.book.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerManager {
    @GetMapping("/manager")
    public String manager(Model model) {

        return "manager/manager";
    }
}
