package book.book.Service;

import book.book.Models.Book;
import book.book.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllShops(){
        List<Book> list =  (List<Book>)bookRepository.findAll();
        return list;
    }

    public List<Book> getByKeyword(String keyword,Pageable pageable){
        return bookRepository.findByKeyword(keyword,pageable);
    }
}
