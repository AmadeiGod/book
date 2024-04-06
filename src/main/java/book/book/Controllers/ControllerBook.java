package book.book.Controllers;

import book.book.Models.Book;
import book.book.Models.Image;
import book.book.Repo.BookRepository;
import book.book.Repo.ImageRepository;
import book.book.Service.BookServices;
import book.book.Service.ImageServices;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ControllerBook {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageServices imageServices;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Book book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model,
                          @RequestParam("image") MultipartFile[] multipartFile) throws IOException {
        List<Image> list = book.getListImages();
        for (MultipartFile multipartFile1 : multipartFile) {
                Image image = imageServices.getimage(multipartFile1,book);
                list.add(image);
        }
        try{
            String imagePre1 = Base64.getEncoder().encodeToString(multipartFile[0].getBytes());
            book.setPhotoprevue(imagePre1);
        }catch (Exception e){
            System.out.println("Нету картинки");
        }
        try{
            String imagePre2 = Base64.getEncoder().encodeToString(multipartFile[1].getBytes());
            book.setPhoto1(imagePre2);
        }catch (Exception e){
            System.out.println("Нету картинки");
        }
        try{
            String imagePre3 = Base64.getEncoder().encodeToString(multipartFile[2].getBytes());
            book.setPhoto2(imagePre3);
        }catch (Exception e){
            System.out.println("Нету картинки");
        }

        book.setListImages(list);
        bookRepository.save(book);


        return "redirect:/index";
    }

    @GetMapping("/home")
    public String showUserList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "home";
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

        imageRepository.deleteAll(book.getListImages());
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
    @Transactional
    @GetMapping("/book/{id}")
    public String bookpage(@PathVariable("id") long id, Model model, HttpServletResponse response) throws IOException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));

        model.addAttribute("book",book);

        return "book/book";
    }

}
