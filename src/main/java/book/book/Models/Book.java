package book.book.Models;

import book.book.Repo.ImageRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min=2, max=50)
    private String name;
    @Size(min=2, max=50)
    private String text;
    @Size(min=2, max = 50)
    private String autor;

    private String price;
    private String proizvoditel;
    private String format;
    private String pereplet;
    private String count_page;


    private String photoprevue;
    private String photo1;
    private String photo2;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> listImages = new ArrayList<>();


    /*@Transient
    public String getPhotosImagePath() {
        if (photoprevue == null ) return null;

        return "/user-photos/" + id + "/" + photoprevue;
    }*/
    public Book() {

    }
}
