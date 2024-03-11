package book.book.Controllers;

import book.book.Models.Book;
import book.book.Models.Cart;
import book.book.Models.User;
import book.book.Repo.BookRepository;
import book.book.Repo.CartRepository;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class ControllerCart {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/cart")
    public String Cart(Model model,Authentication authentication){
        User user = userService.findUserByUsername(authentication.getName());
        model.addAttribute("carts",cartRepository.findAllById(Collections.singleton(user.getId())));
        return "cart";
    }
    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable ("id") long id, Authentication authentication, Model model){
        User user = userService.findUserByUsername(authentication.getName());
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        Optional<Cart> cart1 = cartRepository.findByUsernameIdAndIdBook(user.getId(),book.getId());
        if (cart1.isPresent()){
            Cart cart = cart1.get();
            cart.setCountBook(cart.getCountBook() + 1);
            cartRepository.save(cart);
            System.out.println("Кол-во книги  " + id + " увеличилось на 1");
        }
        if (cart1.isEmpty()){
            Cart cart = new Cart();
            cart.setIdBook(book.getId());
            cart.setUsernameId(user.getId());
            cart.setCountBook(1);
            cartRepository.save(cart);
            System.out.println("Книга номер " + id + " добавлена в корзину");
        }


        return "redirect:/index";
    }
}   
