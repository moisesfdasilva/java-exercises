//package Api;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.List;
import java.util.Optional;

public class Main {
  public static Collection<Student> getAllStudents() {
    return Set.of(
        new Student("2001", "Mary", "Computing"),
        new Student("2002", "John", "Computing"),
        new Student("2003", "Josef", "Pedagogy"),
        new Student("2004", "Ana", "Computing"),
        new Student("2005", "Bernard", "Engineering"),
        new Student("2006", "Joana", "Computing"));
  }

  public static void main(String[] args) {
    Collection<Student> students = getAllStudents();
    List<String> registrations = students.stream()
        .filter(e -> "Computing".equals(e.getCourse()))
        .sorted(Comparator.comparing(Student::getName))
        .map(Student::getRegistration)
        .toList();
    System.out.println(registrations);

    Collection<String> names = List.of("Mary","John","Josef","Ana","Bernard","Joana");
    long people = names.stream().distinct().count();
    System.out.println(people);

    List<Student> computingStudents = students.stream()
      .filter(e -> "Computing".equals(e.getCourse())).toList();
    System.out.println(computingStudents);

    Optional<Student> studentCalledAna = students.stream()
      .filter(e -> "Ana".equals(e.getName())).findAny();
    System.out.println(studentCalledAna);

    Student studentCalledMario = students.stream()
      .filter(e -> "Mario".equals(e.getName())).findAny().orElse(null);
    System.out.println(studentCalledMario);

    List<String> regCompStudents = students.stream()
      .filter(e -> "Computing".equals(e.getCourse()))
      .map(e -> e.getRegistration())
      .toList();
    System.out.println(regCompStudents);
  }
}