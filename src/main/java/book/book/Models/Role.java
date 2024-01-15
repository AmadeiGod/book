package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }
    // Constructors, getters, and setters
}
