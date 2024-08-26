package moses.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import moses.people.model.Person;
import moses.people.service.PersonService;

@RestController
public class PersonController {

  private PersonService service;

  @Autowired
  public PersonController(PersonService service) {
    this.service = service;
  }

  @GetMapping("/person/{name}")
  public ResponseEntity<Person> searchPersonByName(@PathVariable String name) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(service.searchPersonByName(name));
  }

}
