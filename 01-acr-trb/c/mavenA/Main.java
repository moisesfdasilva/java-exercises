import com.google.common.base.Joiner;

// $ mvn package -> build project
// $ mvn test -> run all tests
// $ mvn test -Dtest="SomethingTest" -> run a test
// $ mvn clean -> clean execute files

// $ mvn spring-boot:run -> run spring boot

public class Main {
  public static void main(String[] args) {
    String result = Joiner.on(", ").skipNulls().join("Hello", null, "everybody");
    System.out.println(result);
  }
}
