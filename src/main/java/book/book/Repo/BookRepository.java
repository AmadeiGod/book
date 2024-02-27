package book.book.Repo;

import book.book.Models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "select * from book s where s.name like %:keyword% or s.autor like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);


}
