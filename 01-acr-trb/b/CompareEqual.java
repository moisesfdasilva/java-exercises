import java.util.Scanner;

public class CompareEqual {
  public static void main(String[] args) {
    int num = 5;
    System.out.println(num == 5);

    Scanner scanner = new Scanner(System.in);

    String hello = "Hello";
    System.out.println("Say hello: ");
    String input = scanner.next();
    scanner.close();

    if (hello == input) {
      System.out.println("It won't be print.");
    }
    
    if (hello != input) {
      System.out.println("It will be print.");
    }

    if (hello == hello) {
      System.out.println("It will be print.");
    }
    
    if (hello.equals(input)) {
      System.out.println("It will be print, if you enter with 'Hello'.");
    }
  }
}
