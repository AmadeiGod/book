package book.book.Repo;

import book.book.Models.User;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
