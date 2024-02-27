package book.book.Models;

import jakarta.persistence.ManyToOne;

public class Order {
    @ManyToOne
    private BasketBook basketBook;

}
