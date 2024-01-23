package book.book.Service;
import book.book.DTO.RegDto;
import book.book.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<User> getAll();

    User save(RegDto userRegDto);
}
