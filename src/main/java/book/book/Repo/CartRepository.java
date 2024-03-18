package book.book.Repo;

import book.book.Models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAllByUsernameId(long id);
    Optional<Cart> findByIdBook(long id);
    Optional<Cart> findByUsernameIdAndIdBook(Long id, long id1);
}
