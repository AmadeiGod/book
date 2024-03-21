package book.book.Repo;

import book.book.Models.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book s where s.name like %:keyword% or s.autor like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword, Pageable pageable);


    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);

}
