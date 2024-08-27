package moses.people.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moses.people.PersonNotFoundException;
import moses.people.model.Person;

@Service
public class PersonService {

  private List<Person> people = new ArrayList<Person>(Arrays.asList(
    new Person(0, "John"),
    new Person(1, "Mary"),
    new Person(2, "Peter"),
    new Person(3, "Rose"),
    new Person(4, "Joana"),
    new Person(5, "Zara")
  ));

  public Person searchPersonByName(String name) {
    return this.people.stream()
      .filter(p -> p.getName().equals(name)).findFirst()
      .orElseThrow(() -> new PersonNotFoundException("Person not found."));
  }
}
