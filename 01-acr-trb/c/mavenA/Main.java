import com.google.common.base.Joiner;

// $ mvn package -> build project
// $ mvn test -> run test
// $ mvn clean -> clean execute files

public class Main {
  public static void main(String[] args) {
    String result = Joiner.on(", ").skipNulls().join("Hello", null, "everybody");
    System.out.println(result);
  }
}
