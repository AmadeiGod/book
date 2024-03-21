package book.book.Controllers;

import book.book.Models.Book;
import book.book.Repo.BookRepository;
import book.book.Service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ControllerBook {
    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }

        bookRepository.save(book);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        model.addAttribute("book", book);
        return "update-book";

    }
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid Book book,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-book";
        }

        bookRepository.save(book);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        bookRepository.delete(book);
        return "redirect:/index";
    }


    @Autowired
    private BookServices service;
    @RequestMapping(path = {"/","/search"})
    public String home(Book book, Model model, String keyword,@RequestParam(defaultValue = "1") int page) {
        if(keyword != "") {
            Pageable pageable = PageRequest.of(0,3);
            List<Book> list = bookRepository.findByKeyword(keyword,pageable);
            model.addAttribute("list", list);
            System.out.println(list);

        }else {
            List<Book> list = service.getAllShops();
            Pageable pageable = PageRequest.of(0,6,Sort.by("name"));
            Page<Book> page1 = bookRepository.findAll(pageable);
            model.addAttribute("list", page1);}
            System.out.println("123");
        return "index";
    }
}
