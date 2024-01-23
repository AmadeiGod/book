package book.book.Models;
import jakarta.persistence.*;


import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "users")
@Data
@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @ManyToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Role> roles;
    public User() {

    }
    public User(String username,
                String email, String password,
                Collection<Role> roles) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
