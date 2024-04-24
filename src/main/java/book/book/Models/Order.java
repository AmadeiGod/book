package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Book book;
    private Long orderIdCart;
    private Long orderIdUser;
    private int countBuyBook;

}
