package book.book.Service;


import book.book.DTO.RegDto;
import book.book.Models.Role;
import book.book.Models.User;
import book.book.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(RegDto registrationDto) {

        User user = new User();
                user.setUsername(registrationDto.getUsername());
                user.setEmail(registrationDto.getEmail());
                user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));


        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority
                        (role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {

        return (List<User>) userRepository.findAll();
    }
}
