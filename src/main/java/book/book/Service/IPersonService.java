package book.book.Service;

import book.book.DTO.PersonDTO;
import book.book.Models.Person;

public interface IPersonService {
    Person registerNewUserAccount(PersonDTO personDTO);
}
