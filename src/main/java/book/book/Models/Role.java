package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Constructors, getters, and setters
}
