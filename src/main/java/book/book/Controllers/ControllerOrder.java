package book.book.Controllers;

import book.book.Models.Cart;
import book.book.Models.Order;
import book.book.Models.User;
import book.book.Repo.BookRepository;
import book.book.Repo.CartRepository;
import book.book.Repo.OrderRepository;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerOrder {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("buy")
    public String buy_order(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        List<Cart> cartlist = cartRepository.findAllByUsernameId(user.getId());
        for (Cart cart: cartlist){
            Optional<Cart> cart1 = cartRepository.findByUsernameIdAndIdBook(user.getId(),cart.getIdBook());
            if(cart1.isPresent()){
                Cart cart2 = cart1.get();
                Order order = new Order();
                order.setId_CCart(cart2.getId());
                order.setId_BBook(cart2.getIdBook());
                order.setId_userr(user.getId());
                orderRepository.save(order);
            }
        }
        for (Cart cart: cartlist){
            Optional<Cart> cart1 = cartRepository.findByUsernameIdAndIdBook(user.getId(),cart.getIdBook());
            cartRepository.deleteAllById(Collections.singleton(user.getId()));
            if(cart1.isPresent()){
                Cart cart2 = cart1.get();
                cartRepository.delete(cart2);

            }
        }

        return "redirect:home";
    }
}
