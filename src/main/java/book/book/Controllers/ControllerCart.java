package book.book.Controllers;

import book.book.Models.Book;
import book.book.Models.Cart;
import book.book.Models.User;
import book.book.Repo.BookRepository;
import book.book.Repo.CartRepository;
import book.book.Repo.UserRepository;
import book.book.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

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
        List<Cart> arrayListCart = cartRepository.findAllByUsernameId(user.getId());
        List<Book> arrayListBook = new ArrayList<>();
        for (Cart cart : arrayListCart) {
            Optional<Book> book1 = bookRepository.findById(cart.getIdBook());
            if ( book1.isPresent()){
                Book book = book1.get();
                arrayListBook.add((book));
            }
        }
        model.addAttribute("cartlist", arrayListCart);
        model.addAttribute("arrayli", arrayListBook);
        Map<Cart,Book> maplist= new HashMap<>();
        for (int i = 0; i < arrayListCart.size(); i++) {
            maplist.put(arrayListCart.get(i), arrayListBook.get(i));
        }
        model.addAttribute("maplist",maplist);
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
            System.out.println("Кол-во книги  " + book.getName() + " увеличилось на 1");
        }
        if (cart1.isEmpty()) {
            Cart cart = new Cart();
            cart.setIdBook(book.getId());
            cart.setUsernameId(user.getId());
            cart.setCountBook(1);
            cartRepository.save(cart);
            System.out.println("Книга" + book.getName() + " добавлена в корзину");
        }
        return "redirect:/index";
    }
    @GetMapping("cart/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model,Authentication authentication) {
        User user = userService.findUserByUsername(authentication.getName());
        Cart cart = cartRepository.findByUsernameIdAndIdBook(user.getId(),id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        cartRepository.delete(cart);
        System.out.println("32");
        return "redirect:/cart";
    }
    @GetMapping("cart/edit-delete-countbook/{id}")
    public String cart_edit_delete_countbook(@PathVariable("id") long id, Model model,Authentication authentication) {
        User user = userService.findUserByUsername(authentication.getName());
        Cart cart = cartRepository.findByUsernameIdAndIdBook(user.getId(),id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        if(cart.getCountBook() < 2){
            cartRepository.delete(cart);
        }else{
            cart.setCountBook(cart.getCountBook() - 1);
            cartRepository.save(cart);
        }
        return "redirect:/cart";
    }
    @GetMapping("cart/edit-add-countbook/{id}")
    public String cart_edit_add_countbook(@PathVariable("id") long id, Model model,Authentication authentication) {
        User user = userService.findUserByUsername(authentication.getName());
        Cart cart = cartRepository.findByUsernameIdAndIdBook(user.getId(),id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        cart.setCountBook(cart.getCountBook() + 1);
        cartRepository.save(cart);
        return "redirect:/cart";
    }
}   
