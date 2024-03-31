package book.book.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String contentType;
    private Long size;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
    @ManyToOne
    private Book book;


}
