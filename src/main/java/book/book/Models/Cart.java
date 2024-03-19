package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Map;
import java.util.Set;


@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long usernameId;
    private Long idBook;
    private int countBook;

    public Cart(Long username_id,Long id_book,int count_book){
        this.usernameId = username_id;
        this.idBook = id_book;
        this.countBook = count_book;
    }

    public Cart() {

    }
}
