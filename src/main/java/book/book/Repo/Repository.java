package book.book.Repo;

import book.book.Models.Book;
import book.book.Models.Person;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Person, Long> {


    static boolean findByEmail(String email) {
    }
}
