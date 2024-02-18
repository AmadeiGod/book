package book.book.Service;


import book.book.DTO.RegDto;
import book.book.Models.User;
import book.book.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return new UserDetailService(user);
    }
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserServiceImpl() {

    }


    @Override
    public User save(RegDto registrationDto) {

        User user = new User();
                user.setUsername(registrationDto.getUsername());
                user.setEmail(registrationDto.getEmail());
                user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
                user.setRole("USER");

        return userRepository.save(user);
    }




    @Override
    public List<User> getAll() {

        return (List<User>) userRepository.findAll();
    }
}
