package book.book.Service;

import book.book.DTO.PersonDTO;
import book.book.Models.Person;
import book.book.Repo.Repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Transactional
public class PersonService implements IPersonService{
    @Autowired
    private Repository repository;

    @Override
    public Person registerNewUserAccount(PersonDTO personDTO) throws UserAlreadyExistException {
        if (emailExists(personDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + personDTO.getEmail());
        }

        Person person = new Person();
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setEmail(personDTO.getEmail());
        person.setRoles(Arrays.asList("ROLE_USER"));

        return repository.save(person);
    }

    private boolean emailExists(String email) {
        return Repository.findByEmail(email) != null;
    }

}
