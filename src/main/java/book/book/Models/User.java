package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    @Column(name = "user_enabled")
    private boolean isEnabled;

}
