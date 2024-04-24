package book.book.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/news")
    public String news() {
        return "news";
    }
    @GetMapping("/O-kompanii")
    public String Okompanii() {
        return "O-kompanii";
    }
    @GetMapping("/dostavka-i-oplata")
    public String dostavkaioplata() {
        return "dostavka-i-oplata";
    }
    @GetMapping("/sales")
    public String sales() {
        return "sales";
    }
    @GetMapping("/kontakty")
    public String kontakty() {
        return "kontakty";
    }
    @GetMapping("/")
    public String home2() {
        return "home";
    }
}
