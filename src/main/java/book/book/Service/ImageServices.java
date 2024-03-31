package book.book.Service;

import book.book.Models.Book;
import book.book.Models.Image;
import book.book.Repo.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

@Service
public class ImageServices {
    @Autowired
    private ImageRepository imageRepository;

    public void save(MultipartFile file, Book book) throws IOException {
        Image image = new Image();
        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setContentType(file.getContentType());
        image.setData(Base64.getEncoder().encodeToString(file.getBytes()).getBytes());
        image.setSize(file.getSize());
        imageRepository.save(image);
    }
    public Image getimage(MultipartFile file, Book book) throws IOException {
        Image image = new Image();
        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        image.setContentType(file.getContentType());
        image.setData(Base64.getEncoder().encodeToString(file.getBytes()).getBytes());
        image.setSize(file.getSize());
        return image;
    }
    @Transactional
    public List<Image> listAll(Book book) {
        return imageRepository.findAllByBook(book);
    }

}
