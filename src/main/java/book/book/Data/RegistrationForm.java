package book.book.Data;

import lombok.Data;


@Data
public class RegistrationForm {
    private String password;
    private String name;
    private String street;
/*
    public Person toPerson(PasswordEncoder) {
        return new Person(name, passwordEncoder.encode(password), street);
    }*/
}
