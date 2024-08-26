//package 01-acr-trb.f.Person;

import java.util.Optional;

public class App {

  public static void main(String[] args) {
    Optional<Person> optional = Optional.empty();

    if (optional.isPresent()) {
      System.out.println("Exist person.");
      System.out.println(optional.get().getName());
    } else {
      System.out.println("Non-existent person.");
    }
  }
}