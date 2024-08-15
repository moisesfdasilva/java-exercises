import java.util.Scanner;

public class ScannerA {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What is your name: ");
    String name = scanner.next();
    System.out.println("Welcome, " + name + "!");
    scanner.close();
  }
}
