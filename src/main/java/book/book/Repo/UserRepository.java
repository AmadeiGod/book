package book.book.Repo;

import book.book.Models.User;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
    User findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String username);
}
