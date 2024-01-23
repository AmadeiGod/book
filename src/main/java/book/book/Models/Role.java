package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    public Role() {

    }
    public Role(String name) {
        super();
        this.name = name;
    }


    // Constructors, getters, and setters
}
